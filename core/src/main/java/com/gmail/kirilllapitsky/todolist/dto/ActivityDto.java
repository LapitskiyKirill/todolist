package com.gmail.kirilllapitsky.todolist.dto;

import com.gmail.kirilllapitsky.todolist.entity.Scheduled;

import java.time.LocalDateTime;

public class ActivityDto {
    public Long id;

    public  Long scheduledId;

    public  LocalDateTime complete;

    public  LocalDateTime date;

    public ActivityDto(Long id, Long scheduledId, LocalDateTime complete, LocalDateTime date) {
        this.id = id;
        this.scheduledId = scheduledId;
        this.complete = complete;
        this.date = date;
    }
}
