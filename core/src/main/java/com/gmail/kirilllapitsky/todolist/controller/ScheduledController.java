package com.gmail.kirilllapitsky.todolist.controller;

import com.gmail.kirilllapitsky.todolist.dto.EditScheduledDto;
import com.gmail.kirilllapitsky.todolist.dto.NewScheduledDto;
import com.gmail.kirilllapitsky.todolist.dto.ScheduledDto;
import com.gmail.kirilllapitsky.todolist.entity.User;
import com.gmail.kirilllapitsky.todolist.exception.AuthenticationException;
import com.gmail.kirilllapitsky.todolist.exception.NoSuchEntityException;
import com.gmail.kirilllapitsky.todolist.service.AuthenticationService;
import com.gmail.kirilllapitsky.todolist.service.ScheduledService;
import com.gmail.kirilllapitsky.todolist.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("scheduled")
public class ScheduledController {

    private final ScheduledService scheduledService;

    private final AuthenticationService authenticationService;

    @Autowired
    public ScheduledController(ScheduledService scheduledService, AuthenticationService authenticationService) {
        this.scheduledService = scheduledService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("create")
    public ScheduledDto create(@RequestHeader("token") String token,
                               @RequestBody NewScheduledDto newScheduledDto)
            throws NoSuchEntityException, AuthenticationException {
        User user = authenticationService.validate(token);

        return Mapper.map(scheduledService.create(user, newScheduledDto), ScheduledDto.class);
    }

    @GetMapping("delete")
    public void delete(@RequestHeader("token") String token, @RequestParam("taskId") Long taskId)
            throws AuthenticationException, NoSuchEntityException {
        User user = authenticationService.validate(token);

        scheduledService.delete(user, taskId);
    }

    @GetMapping("getAll")
    public List<ScheduledDto> getAllScheduled(@RequestHeader("token") String token) {
        User user = authenticationService.validate(token);
        return Mapper.mapAll(scheduledService.getAll(user), ScheduledDto.class);
    }

    @PostMapping("edit")
    public ScheduledDto edit(@RequestHeader("token") String token,
                             @RequestBody EditScheduledDto editScheduledDto) throws NoSuchEntityException, AuthenticationException {
        User user = authenticationService.validate(token);

        return Mapper.map(scheduledService.edit(user, editScheduledDto), ScheduledDto.class);
    }

}