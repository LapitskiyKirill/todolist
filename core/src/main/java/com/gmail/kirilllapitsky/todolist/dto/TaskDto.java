package com.gmail.kirilllapitsky.todolist.dto;

import com.gmail.kirilllapitsky.todolist.entity.TaskCategory;

import java.time.LocalDateTime;

public class TaskDto {
    public Long id;
    public TaskCategory category;
    public String text;
    public LocalDateTime deadline;
    public boolean completed;

    public TaskDto() {

    }

    public TaskDto(Long id, TaskCategory category, String text, LocalDateTime deadline, boolean completed) {
        this.id = id;
        this.category = category;
        this.text = text;
        this.deadline = deadline;
        this.completed = completed;
    }
}
