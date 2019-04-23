package com.gmail.kirilllapitsky.todolist.entity;

import com.gmail.kirilllapitsky.todolist.entity.enums.Periodicity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "scheduled")
public class Scheduled {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @Column(name = "\"from\"")
    private LocalDateTime from;

    @Column(name = "periodicity")
    private Periodicity periodicity;

    public Scheduled() {
    }

    public Scheduled(Task task, LocalDateTime from, Periodicity periodicity) {
        this.task = task;
        this.from = from;
        this.periodicity = periodicity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

}
