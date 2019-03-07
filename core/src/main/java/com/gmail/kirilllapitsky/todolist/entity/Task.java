package com.gmail.kirilllapitsky.todolist.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "text")
    private String text;

    @Column(name = "deadline")
    private LocalDateTime deadline;

    @Column(name = "completed")
    private boolean completed;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "main_task_subtask",
            joinColumns = @JoinColumn(name = "main_task_id"),
            inverseJoinColumns = @JoinColumn(name = "subtask_id")
    )
    private List<Task> subtasks;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private TaskCategory category;

    public Task() {
    }

    public Task(User user, String text, LocalDateTime deadline, boolean completed, List<Task> subtasks, TaskCategory category) {
        this.user = user;
        this.text = text;
        this.deadline = deadline;
        this.completed = completed;
        this.subtasks = subtasks;
        this.category = category;
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

    public List<Task> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<Task> subtasks) {
        this.subtasks = subtasks;
    }

    public TaskCategory getCategory() {
        return category;
    }

    public void setCategory(TaskCategory category) {
        this.category = category;
    }
}
