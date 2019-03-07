package com.gmail.kirilllapitsky.todolist.controller;

import com.gmail.kirilllapitsky.todolist.dto.NewTaskDto;
import com.gmail.kirilllapitsky.todolist.dto.TaskDto;
import com.gmail.kirilllapitsky.todolist.entity.Mapper;
import com.gmail.kirilllapitsky.todolist.entity.Task;
import com.gmail.kirilllapitsky.todolist.entity.User;
import com.gmail.kirilllapitsky.todolist.exception.AuthenticationException;
import com.gmail.kirilllapitsky.todolist.exception.NoSuchTaskException;
import com.gmail.kirilllapitsky.todolist.service.AuthenticationService;
import com.gmail.kirilllapitsky.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("task")
@Transactional
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("create")
    public TaskDto create(@RequestHeader("token") String token, @RequestBody NewTaskDto newTaskDto) {
        User user = authenticationService.validate(token);
        Task task = taskService.create(user, newTaskDto);
        return Mapper.map(task);
    }

    @GetMapping("delete")
    public void delete(@RequestHeader("token") String token, @RequestParam("taskId") Long taskId) throws NoSuchTaskException, AuthenticationException {
        User user = authenticationService.validate(token);
        taskService.delete(user, taskId);
    }

    @PostMapping("edit")
    public TaskDto edit(@RequestHeader("token") String token, @RequestParam("taskId") Long taskId, @RequestBody NewTaskDto newTaskDto) throws NoSuchTaskException, AuthenticationException {
        User user = authenticationService.validate(token);
        return taskService.edit(user, taskId, newTaskDto);
    }

    @GetMapping("getAll")
    public List<TaskDto> getAll(@RequestHeader("token") String token) {
        User user = authenticationService.validate(token);
        return taskService.all(user);
    }

    @PostMapping("check")
    public void checking(@RequestHeader("token") String token, @RequestParam("taskId") Long taskId) throws NoSuchTaskException, AuthenticationException {
        User user = authenticationService.validate(token);
        taskService.check(user, taskId);
    }

    @PostMapping("uncheck")
    public void unChecking(@RequestHeader("token") String token, @RequestParam("taskId") Long taskId) throws NoSuchTaskException, AuthenticationException {
        User user = authenticationService.validate(token);
        taskService.unCheck(user, taskId);
    }
}
