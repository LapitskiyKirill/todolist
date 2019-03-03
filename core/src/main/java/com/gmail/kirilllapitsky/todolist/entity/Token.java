package com.gmail.kirilllapitsky.todolist.entity;

public class Token {
    private Long id;
    private String token;
    private User user;

    public Token() {
    }

    public Token(String login, User user) {
        this.token = login;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
