package com.gmail.kirilllapitsky.todolist.controller;

import com.gmail.kirilllapitsky.todolist.exception.NoSuchEntityException;
import com.gmail.kirilllapitsky.todolist.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("token")
public class TokenController {

    private final TokenService tokenService;

    @Autowired
    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

        @GetMapping("delete")
    public void delete(@RequestParam("token") String token) throws NoSuchEntityException {
       tokenService.delete(token);
    }
}
