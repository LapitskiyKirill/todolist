package com.gmail.kirilllapitsky.todolist.dto;

public class NewTaskDto {
    public String text;
    public String deadline;
    public String category;

    public NewTaskDto() {
    }

    public NewTaskDto(String text) {
        this.text = text;
    }
}

