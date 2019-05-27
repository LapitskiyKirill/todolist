package com.gmail.kirilllapitsky.todolist.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class EditTaskDto {
    public String text;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    public LocalDateTime deadline;
    public String category;

    public EditTaskDto() {
    }

    public EditTaskDto(String text) {
        this.text = text;
    }

    public EditTaskDto(String text, LocalDateTime deadline, String category) {
        this.text = text;
        this.deadline = deadline;
        this.category = category;
    }
}

