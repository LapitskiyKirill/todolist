package com.gmail.kirilllapitsky.todolist.dto;

public class NewTaskDto {
    public String text;
    public String executionTime;
    public String category;

    public NewTaskDto() {
    }

    public NewTaskDto(String text, String executionTime, String category) {
        this.text = text;
        this.executionTime = executionTime;
        this.category = category;
    }
}
