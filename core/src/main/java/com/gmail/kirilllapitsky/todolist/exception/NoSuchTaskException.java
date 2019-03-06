package com.gmail.kirilllapitsky.todolist.exception;

public class NoSuchTaskException extends Exception{
    public NoSuchTaskException() {
    }

    public NoSuchTaskException(String message) {
        super(message);
    }
}
