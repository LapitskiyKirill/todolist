package com.gmail.kirilllapitsky.todolist.controller;

import com.gmail.kirilllapitsky.todolist.dto.AuthenticationUserDto;
import com.gmail.kirilllapitsky.todolist.dto.RegisterUserDto;
import com.gmail.kirilllapitsky.todolist.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("register")
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @PostMapping
    public void register(@RequestBody RegisterUserDto registerUserDto) {
        registerService.register(registerUserDto);
    }
}
