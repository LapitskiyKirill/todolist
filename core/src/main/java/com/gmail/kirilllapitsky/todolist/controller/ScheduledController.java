package com.gmail.kirilllapitsky.todolist.controller;

import com.gmail.kirilllapitsky.todolist.dto.EditScheduledDto;
import com.gmail.kirilllapitsky.todolist.dto.NewScheduledDto;
import com.gmail.kirilllapitsky.todolist.dto.ScheduledDto;
import com.gmail.kirilllapitsky.todolist.exception.AuthenticationException;
import com.gmail.kirilllapitsky.todolist.exception.NoSuchTaskException;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("scheduled")
public class ScheduledController {

    @Autowired
    private ScheduledService scheduledService;

    @PostMapping("create")
    public ScheduledDto create(@RequestHeader("token") String token,
                               @RequestBody NewScheduledDto newScheduledDto)
            throws NoSuchTaskException, AuthenticationException {

        return Mapper.map(scheduledService.create(token, newScheduledDto), ScheduledDto.class);
    }

    @GetMapping("delete")
    public void delete(@RequestHeader("token") String token, @RequestParam("taskId") Long taskId)
            throws AuthenticationException, NoSuchTaskException {

        scheduledService.delete(token, taskId);
    }

    @GetMapping("getAllScheduled")
    public List<ScheduledDto> getScheduled(@RequestHeader("token") String token) {
        return scheduledService.getScheduled(token).stream()
                .map(t -> Mapper.map(t, ScheduledDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping("edit")
    public ScheduledDto edit(@RequestHeader("token") String token,
                             @RequestBody EditScheduledDto editScheduledDto) throws NoSuchTaskException, AuthenticationException {

        return Mapper.map(scheduledService.edit(token, editScheduledDto), ScheduledDto.class);
    }


}