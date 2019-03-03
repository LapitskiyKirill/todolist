package com.gmail.kirilllapitsky.todolist.dto;

public class TaskDto {
    public Long id;
    public String category;
    public String text;
    public String executionTime;
    public boolean completed;

    public TaskDto(Long id, String category, String text, String executionTime, boolean completed) {
        this.id = id;
        this.category = category;
        this.text = text;
        this.executionTime = executionTime;
        this.completed = completed;
    }

    public TaskDto() {

    }
}
