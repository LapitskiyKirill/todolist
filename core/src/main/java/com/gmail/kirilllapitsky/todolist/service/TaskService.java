package com.gmail.kirilllapitsky.todolist.service;

import com.gmail.kirilllapitsky.todolist.dto.NewTaskDto;
import com.gmail.kirilllapitsky.todolist.dto.TaskDto;
import com.gmail.kirilllapitsky.todolist.entity.Mapper;
import com.gmail.kirilllapitsky.todolist.entity.Task;
import com.gmail.kirilllapitsky.todolist.entity.TaskCategory;
import com.gmail.kirilllapitsky.todolist.entity.User;
import com.gmail.kirilllapitsky.todolist.exception.AuthenticationException;
import com.gmail.kirilllapitsky.todolist.exception.NoSuchTaskException;
import com.gmail.kirilllapitsky.todolist.repository.TaskCategoryRepository;
import com.gmail.kirilllapitsky.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskCategoryRepository taskCategoryRepository;

    public Task create(User user, NewTaskDto newTaskDto) {
        TaskCategory taskCategory = null;

        if (newTaskDto.category != null && taskCategoryRepository.findByValueAndUser(newTaskDto.category, user).isPresent()) {
            Optional<TaskCategory> taskCategoryOptional = taskCategoryRepository.findByValueAndUser(newTaskDto.category, user);
            taskCategory = taskCategoryOptional.orElseGet(() -> new TaskCategory(user, newTaskDto.category));
        } else if (newTaskDto.category != null){
            taskCategory = new TaskCategory(user, newTaskDto.category);
            taskCategoryRepository.save(taskCategory);
        }

        Task task = new Task(
                user,
                newTaskDto.text,
                newTaskDto.deadline,
                false,
                newTaskDto.subTasks
                        .stream()
                        .map(d -> new Task(user, d.text, d.deadline, false, Collections.emptyList(), null))
                        .collect(Collectors.toList()),
                taskCategory
        );
        return taskRepository.save(task);
    }

    public void delete(User user, Long taskId) throws NoSuchTaskException, AuthenticationException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchTaskException());
        if (task.getUser().getId().equals(user.getId()))
            taskRepository.delete(task);
        else
            throw new AuthenticationException();
    }

    //    TODO: fix taskCategory duplication
    public TaskDto edit(User user, Long taskId, NewTaskDto newTaskDto) throws NoSuchTaskException, AuthenticationException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchTaskException());

        task.setCategory(new TaskCategory(user, newTaskDto.category));
        task.setText(newTaskDto.text);
        task.setDeadline(newTaskDto.deadline);

        if (!task.getUser().getId().equals(user.getId()))
            throw new AuthenticationException();

        taskRepository.save(task);

        return Mapper.map(task);
    }

    public void check(User user, Long taskId) throws NoSuchTaskException, AuthenticationException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchTaskException());

        if (!task.getUser().getId().equals(user.getId()))
            throw new AuthenticationException();

        task.setCompleted(true);

        taskRepository.save(task);
    }

    public void unCheck(User user, Long taskId) throws AuthenticationException, NoSuchTaskException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchTaskException());

        if (!task.getUser().getId().equals(user.getId()))
            throw new AuthenticationException();

        task.setCompleted(false);

        taskRepository.save(task);
    }

    public List<TaskDto> all(User user) {
        return Mapper.mapAll(user.getTasks());
    }
}

