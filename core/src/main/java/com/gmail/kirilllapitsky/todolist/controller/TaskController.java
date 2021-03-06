package com.gmail.kirilllapitsky.todolist.controller;

import com.gmail.kirilllapitsky.todolist.dto.EditTaskDto;
import com.gmail.kirilllapitsky.todolist.dto.NewTaskDto;
import com.gmail.kirilllapitsky.todolist.dto.TaskDto;
import com.gmail.kirilllapitsky.todolist.entity.Task;
import com.gmail.kirilllapitsky.todolist.entity.User;
import com.gmail.kirilllapitsky.todolist.exception.AuthenticationException;
import com.gmail.kirilllapitsky.todolist.exception.NoSuchEntityException;
import com.gmail.kirilllapitsky.todolist.service.AuthenticationService;
import com.gmail.kirilllapitsky.todolist.service.TaskService;
import com.gmail.kirilllapitsky.todolist.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("task")
@Transactional
public class TaskController {

    private final TaskService taskService;

    private final AuthenticationService authenticationService;

    @Autowired
    public TaskController(TaskService taskService, AuthenticationService authenticationService) {
        this.taskService = taskService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("create")
    public TaskDto create(@RequestHeader("token") String token, @RequestBody NewTaskDto newTaskDto) throws NoSuchEntityException {
        User user = authenticationService.validate(token);
        Task task = taskService.create(user, newTaskDto);
        return Mapper.map(task, TaskDto.class);
    }

    @GetMapping("delete")
    public void delete(@RequestHeader("token") String token, @RequestParam("taskId") Long taskId) throws NoSuchEntityException, AuthenticationException {
        User user = authenticationService.validate(token);
        taskService.delete(user, taskId);
    }

    @PostMapping("edit")
    public TaskDto edit(@RequestHeader("token") String token, @RequestParam("taskId") Long taskId, @RequestBody EditTaskDto editTaskDto) throws NoSuchEntityException, AuthenticationException {
        User user = authenticationService.validate(token);
        return taskService.edit(user, taskId, editTaskDto);
    }

    @GetMapping("getAll")
    public List<TaskDto> getAll(@RequestHeader("token") String token) throws NoSuchEntityException {
        User user = authenticationService.validate(token);
        return taskService.all(user);
    }

    @GetMapping("getByCategory")
    public List<TaskDto> getByCategory(@RequestHeader("token") String token, @RequestParam("category") String category) throws NoSuchEntityException {
        User user = authenticationService.validate(token);
        return taskService.getByCategory(user, category);
    }

    @GetMapping("check")
    public void checking(@RequestHeader("token") String token, @RequestParam("taskId") Long taskId) throws NoSuchEntityException, AuthenticationException {
        User user = authenticationService.validate(token);
        taskService.check(user, taskId);
    }

    @GetMapping("uncheck")
    public void unChecking(@RequestHeader("token") String token, @RequestParam("taskId") Long taskId) throws NoSuchEntityException, AuthenticationException {
        User user = authenticationService.validate(token);
        taskService.unCheck(user, taskId);
    }

    @GetMapping("checked")
    public List<TaskDto> allChecked(@RequestHeader("token") String token) throws AuthenticationException, NoSuchEntityException {
        User user = authenticationService.validate(token);
        return taskService.allChecked(user);
    }

    @GetMapping("unchecked")
    public List<TaskDto> allUnchecked(@RequestHeader("token") String token) throws AuthenticationException, NoSuchEntityException {
        User user = authenticationService.validate(token);
        return taskService.allUnchecked(user);
    }
}
