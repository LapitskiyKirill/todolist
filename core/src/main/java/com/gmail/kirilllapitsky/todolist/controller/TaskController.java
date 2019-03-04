package com.gmail.kirilllapitsky.todolist.controller;

import com.gmail.kirilllapitsky.todolist.dto.NewTaskDto;
import com.gmail.kirilllapitsky.todolist.entity.Token;
import com.gmail.kirilllapitsky.todolist.entity.User;
import com.gmail.kirilllapitsky.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("create")
    public void create(@RequestHeader("token") Token token, @RequestBody NewTaskDto newTaskDto) {
        taskService.create(token, newTaskDto);
    }

    @PostMapping("delete")
    public void delete(User user, NewTaskDto newTaskDto) {

    }

}
