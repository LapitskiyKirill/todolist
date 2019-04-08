package com.gmail.kirilllapitsky.todolist.exception;

public class AuthenticationException extends Exception {
    public AuthenticationException() {
    }

    public AuthenticationException(String message) {
        super(message);
    }
}
