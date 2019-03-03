package com.gmail.kirilllapitsky.todolist.dto;

public class AuthenticationUserDto {
    public String login;
    public String password;

    public AuthenticationUserDto() {
    }

    public AuthenticationUserDto(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
