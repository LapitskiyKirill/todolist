package com.gmail.kirilllapitsky.todolist.dto;

public class UserDto {
    public Long id;
    public String password;

    public UserDto() {
    }

    public UserDto(Long id, String password) {
        this.id = id;
        this.password = password;
    }
}
