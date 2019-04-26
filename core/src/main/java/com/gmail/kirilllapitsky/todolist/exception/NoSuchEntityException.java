package com.gmail.kirilllapitsky.todolist.exception;

public class NoSuchEntityException extends Exception {
    public NoSuchEntityException() {
    }

    public NoSuchEntityException(String message) {
        super(message);
    }
}
