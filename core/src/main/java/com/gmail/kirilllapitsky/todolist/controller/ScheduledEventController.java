package com.gmail.kirilllapitsky.todolist.controller;

import com.gmail.kirilllapitsky.todolist.dto.DateRangeDto;
import com.gmail.kirilllapitsky.todolist.dto.ScheduledEventDto;
import com.gmail.kirilllapitsky.todolist.entity.User;
import com.gmail.kirilllapitsky.todolist.exception.AuthenticationException;
import com.gmail.kirilllapitsky.todolist.exception.NoSuchEntityException;
import com.gmail.kirilllapitsky.todolist.service.AuthenticationService;
import com.gmail.kirilllapitsky.todolist.service.ScheduledActivityService;
import com.gmail.kirilllapitsky.todolist.service.ScheduledService;
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
@RequestMapping("activity")
public class ScheduledEventController {
    private final ScheduledActivityService scheduledActivityService;

    private final ScheduledService scheduledService;

    private final AuthenticationService authenticationService;

    @Autowired
    public ScheduledEventController(ScheduledActivityService scheduledActivityService, ScheduledService scheduledService, AuthenticationService authenticationService) {
        this.scheduledActivityService = scheduledActivityService;
        this.scheduledService = scheduledService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("complete")
    public void complete(@RequestHeader("token") String token, @RequestParam("scheduledActivityId") Long scheduledActivityId) throws NoSuchEntityException {
        User user = authenticationService.validate(token);
        scheduledActivityService.complete(user, scheduledActivityId);
    }

    @GetMapping("uncheck")
    public void uncheck(@RequestHeader("token") String token, @RequestParam("scheduledActivityId") Long scheduledActivityId) throws NoSuchEntityException {
        User user = authenticationService.validate(token);
        scheduledActivityService.uncheck(user, scheduledActivityId);
    }

    @PostMapping("get")
    public List<ScheduledEventDto> getEvents(@RequestHeader("token") String token, @RequestParam("taskId") Long taskId, @RequestBody DateRangeDto dateRangeDto) throws NoSuchEntityException, AuthenticationException {
        User user = authenticationService.validate(token);
        return scheduledService.getEvents(user, taskId, dateRangeDto);
    }

}
