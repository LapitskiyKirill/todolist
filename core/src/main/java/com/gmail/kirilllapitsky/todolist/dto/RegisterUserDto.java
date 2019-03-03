package com.gmail.kirilllapitsky.todolist.dto;

public class RegisterUserDto {
    public String login;
    public String password;

    public RegisterUserDto() {
    }

    public RegisterUserDto(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
