package com.gmail.kirilllapitsky.todolist.entity;

import java.time.LocalDateTime;

public class Task {
    private Long id;
    private User user;
    private String category;
    private String text;
    private LocalDateTime deadline;
    private boolean completed;

    public Task() {
    }

    public Task(User user, String category, String text, LocalDateTime deadline, boolean completed) {
        this.user = user;
        this.category = category;
        this.text = text;
        this.deadline = deadline;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
