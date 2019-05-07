package com.gmail.kirilllapitsky.todolist.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public class TaskDto {
    public Long id;
    public CategoryDto category;
    public String text;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime deadline;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime completed;
    public List<TaskDto> subtasks;

    public TaskDto() {

    }

    public TaskDto(Long id, CategoryDto category, String text, LocalDateTime deadline, LocalDateTime completed, List<TaskDto> subtasks) {
        this.id = id;
        this.category = category;
        this.text = text;
        this.deadline = deadline;
        this.completed = completed;
        this.subtasks = subtasks;
    }
}
