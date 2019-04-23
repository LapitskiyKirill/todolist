package com.gmail.kirilllapitsky.todolist.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "scheduled_activity")
public class ScheduledActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "scheduled_id")
    private Scheduled scheduled;

    @Column(name = "complete")
    private LocalDateTime complete;

    public ScheduledActivity() {
    }

    public ScheduledActivity(Scheduled scheduled, LocalDateTime complete) {
        this.scheduled = scheduled;
        this.complete = complete;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Scheduled getScheduled() {
        return scheduled;
    }

    public void setScheduled(Scheduled scheduled) {
        this.scheduled = scheduled;
    }

    public LocalDateTime getComplete() {
        return complete;
    }

    public void setComplete(LocalDateTime complete) {
        this.complete = complete;
    }
}
