package com.gmail.kirilllapitsky.todolist.dto;

import java.time.LocalDateTime;
import java.util.List;

public class TaskDto {
    public Long id;
    public String category;
    public String text;
    public LocalDateTime deadline;
    public boolean completed;
    public List<TaskDto> subtasks;

    public TaskDto() {

    }

    public TaskDto(Long id, String category, String text, LocalDateTime deadline, boolean completed, List<TaskDto> subtasks) {
        this.id = id;
        this.category = category;
        this.text = text;
        this.deadline = deadline;
        this.completed = completed;
        this.subtasks = subtasks;
    }
}
