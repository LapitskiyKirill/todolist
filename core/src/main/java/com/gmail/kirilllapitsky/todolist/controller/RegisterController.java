package com.gmail.kirilllapitsky.todolist.controller;

import com.gmail.kirilllapitsky.todolist.dto.AuthenticationUserDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("register")
public class RegisterController {
    @PostMapping
    void register(@RequestBody AuthenticationUserDto authenticationUserDto){

    }
}
