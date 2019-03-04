package com.gmail.kirilllapitsky.todolist.service;

import com.gmail.kirilllapitsky.todolist.dto.NewTaskDto;
import com.gmail.kirilllapitsky.todolist.dto.TaskDto;
import com.gmail.kirilllapitsky.todolist.entity.Task;
import com.gmail.kirilllapitsky.todolist.entity.Token;
import com.gmail.kirilllapitsky.todolist.entity.User;
import com.gmail.kirilllapitsky.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class TaskService {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private TaskRepository taskRepository;

    public TaskDto create(Token token, NewTaskDto newTaskDto){
        User user = authenticationService.validate(token.getValue());
        Task task = new Task(user, newTaskDto.text);
        taskRepository.save(task);
        return new TaskDto(task.getId(), task.getCategory(), task.getText(), task.getDeadline(), false);
    }
}

