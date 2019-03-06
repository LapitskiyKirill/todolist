package com.gmail.kirilllapitsky.todolist.service;

import com.gmail.kirilllapitsky.todolist.dto.NewTaskDto;
import com.gmail.kirilllapitsky.todolist.dto.TaskDto;
import com.gmail.kirilllapitsky.todolist.entity.Task;
import com.gmail.kirilllapitsky.todolist.entity.User;
import com.gmail.kirilllapitsky.todolist.exception.AuthenticationException;
import com.gmail.kirilllapitsky.todolist.exception.NoSuchTaskException;
import com.gmail.kirilllapitsky.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public TaskDto create(User user, NewTaskDto newTaskDto) {
        Task task = new Task(user, newTaskDto.text);
        taskRepository.save(task);
        return new TaskDto(task.getId(), task.getCategory(), task.getText(), task.getDeadline(), task.isCompleted());
    }

    public void delete(User user, Long taskId) throws NoSuchTaskException, AuthenticationException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchTaskException());
        if (task.getUser().getId().equals(user.getId()))
            taskRepository.delete(task);
        else
            throw new AuthenticationException();
    }

    public TaskDto edit(User user, Long taskId, NewTaskDto newTaskDto) throws NoSuchTaskException, AuthenticationException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchTaskException());

        task.setCategory(newTaskDto.category);
        task.setText(newTaskDto.text);
        task.setDeadline(newTaskDto.deadline);
        if (!task.getUser().getId().equals(user.getId()))
            throw new AuthenticationException();
        taskRepository.save(task);
        return new TaskDto(task.getId(), task.getCategory(), task.getText(), task.getDeadline(), task.isCompleted());
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
}

