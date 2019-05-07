package com.gmail.kirilllapitsky.todolist.service;

import com.gmail.kirilllapitsky.todolist.dto.CategoryDto;
import com.gmail.kirilllapitsky.todolist.dto.NewTaskDto;
import com.gmail.kirilllapitsky.todolist.dto.TaskDto;
import com.gmail.kirilllapitsky.todolist.entity.Task;
import com.gmail.kirilllapitsky.todolist.entity.TaskCategory;
import com.gmail.kirilllapitsky.todolist.entity.User;
import com.gmail.kirilllapitsky.todolist.exception.AuthenticationException;
import com.gmail.kirilllapitsky.todolist.exception.NoSuchEntityException;
import com.gmail.kirilllapitsky.todolist.repository.TaskCategoryRepository;
import com.gmail.kirilllapitsky.todolist.repository.TaskRepository;
import com.gmail.kirilllapitsky.todolist.repository.UserRepository;
import com.gmail.kirilllapitsky.todolist.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;

    private final TaskCategoryRepository taskCategoryRepository;

    private final UserRepository userRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, TaskCategoryRepository taskCategoryRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.taskCategoryRepository = taskCategoryRepository;
        this.userRepository = userRepository;
    }

    public Task create(User user, NewTaskDto newTaskDto) {
        TaskCategory taskCategory = null;
        User admin = userRepository.findByLogin("admin");
        if (taskCategoryRepository.findByValueAndUser(newTaskDto.category, admin).isPresent()) {
            taskCategory = taskCategoryRepository.findByValueAndUser(newTaskDto.category, admin).get();
        } else if (newTaskDto.category != null && taskCategoryRepository.findByValueAndUser(newTaskDto.category, user).isPresent()) {
            Optional<TaskCategory> taskCategoryOptional = taskCategoryRepository.findByValueAndUser(newTaskDto.category, user);
            taskCategory = taskCategoryOptional.orElseGet(() -> new TaskCategory(user, newTaskDto.category));
        } else if (newTaskDto.category != null) {
            taskCategory = new TaskCategory(user, newTaskDto.category);
            taskCategoryRepository.save(taskCategory);
        }

        Task task = new Task(
                user,
                newTaskDto.text,
                newTaskDto.deadline,
                null,
                newTaskDto.subTasks
                        .stream()
                        .map(d -> new Task(user, d.text, d.deadline, null, Collections.emptyList(), null))
                        .collect(Collectors.toList()),
                taskCategory
        );
        return taskRepository.save(task);
    }

    public void delete(User user, Long taskId) throws NoSuchEntityException, AuthenticationException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(NoSuchEntityException::new);
        if (task.getUser().getId().equals(user.getId()))
            taskRepository.delete(task);
        else
            throw new AuthenticationException();
    }

    public TaskDto edit(User user, Long taskId, NewTaskDto newTaskDto) throws NoSuchEntityException, AuthenticationException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(NoSuchEntityException::new);

        task.setCategory(new TaskCategory(user, newTaskDto.category));
        task.setText(newTaskDto.text);
        task.setDeadline(newTaskDto.deadline);

        if (!task.getUser().getId().equals(user.getId()))
            throw new AuthenticationException();

        taskRepository.save(task);

        return Mapper.map(task, TaskDto.class);
    }

    public void check(User user, Long taskId) throws NoSuchEntityException, AuthenticationException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(NoSuchEntityException::new);

        if (!task.getUser().getId().equals(user.getId()))
            throw new AuthenticationException();

        task.setCompleted(LocalDateTime.now());

        taskRepository.save(task);
    }

    public void unCheck(User user, Long taskId) throws AuthenticationException, NoSuchEntityException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(NoSuchEntityException::new);

        if (!task.getUser().getId().equals(user.getId()))
            throw new AuthenticationException();

        task.setCompleted(null);

        taskRepository.save(task);
    }

    public List<TaskDto> all(User user) {
        return Mapper.mapAll(user.getTasks(), TaskDto.class);
    }

    public List<TaskDto> allChecked(User user) {
        return Mapper.mapAll(user.getTasks(), TaskDto.class)
                .stream()
                .filter(t -> t.completed != null)
                .collect(Collectors.toList());
    }

    public List<TaskDto> allUnchecked(User user) {
        return Mapper.mapAll(user.getTasks(), TaskDto.class)
                .stream()
                .filter(t -> t.completed == null)
                .collect(Collectors.toList());
    }

    public List<TaskDto> getByCategory(User user, String category) throws NoSuchEntityException {
        List<TaskDto> tasks = Mapper.mapAll(user.getTasks(), TaskDto.class);
        TaskCategory taskCategory;
        User admin = userRepository.findByLogin("admin");
        if (taskCategoryRepository.findByValueAndUser(category, user).isPresent())
            taskCategory = taskCategoryRepository.findByValueAndUser(category, user).get();
        else if (taskCategoryRepository.findByValueAndUser(category, admin).isPresent())
            taskCategory = taskCategoryRepository.findByValueAndUser(category, admin).get();
        else
            throw new NoSuchEntityException("no such category");
        return tasks
                .stream()
                .filter(t -> t.category.value.equals(taskCategory.getValue()))
                .collect(Collectors.toList());
    }
}