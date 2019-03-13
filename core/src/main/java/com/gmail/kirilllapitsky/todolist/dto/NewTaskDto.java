package com.gmail.kirilllapitsky.todolist.dto;

import java.time.LocalDateTime;
import java.util.List;

public class NewTaskDto {
    public String text;
    public LocalDateTime deadline;
    public String category;
    public List<NewTaskDto> subTasks;

    public NewTaskDto() {
    }

    public NewTaskDto(String text) {
        this.text = text;
    }

    public NewTaskDto(String text, LocalDateTime deadline, String category, List<NewTaskDto> subTasks) {
        this.text = text;
        this.deadline = deadline;
        this.category = category;
        this.subTasks = subTasks;
    }
}

