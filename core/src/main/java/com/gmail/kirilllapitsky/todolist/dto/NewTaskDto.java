package com.gmail.kirilllapitsky.todolist.dto;

import com.gmail.kirilllapitsky.todolist.entity.TaskCategory;

import java.time.LocalDateTime;

public class NewTaskDto {
    public String text;
    public LocalDateTime deadline;
    public TaskCategory category;

    public NewTaskDto() {
    }

    public NewTaskDto(String text) {
        this.text = text;
    }
}

