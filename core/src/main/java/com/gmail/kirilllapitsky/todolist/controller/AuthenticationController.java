package com.gmail.kirilllapitsky.todolist.controller;

import com.gmail.kirilllapitsky.todolist.dto.AuthenticationUserDto;
import com.gmail.kirilllapitsky.todolist.dto.TokenDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("authenticate")
public class AuthenticationController {
    @PostMapping
    public TokenDto authenticate(@RequestBody AuthenticationUserDto authenticationUserDto) {

        return new TokenDto();

    }


}
