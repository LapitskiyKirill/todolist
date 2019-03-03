package com.gmail.kirilllapitsky.todolist.controller;

import com.gmail.kirilllapitsky.todolist.dto.NewTaskDto;
import com.gmail.kirilllapitsky.todolist.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("task")
public class TaskController {
    @PostMapping("create")
    public void create(User user, NewTaskDto newTaskDto) {

    }

    @PostMapping("delete")
    public void delete(User user, NewTaskDto newTaskDto) {

    }

}
