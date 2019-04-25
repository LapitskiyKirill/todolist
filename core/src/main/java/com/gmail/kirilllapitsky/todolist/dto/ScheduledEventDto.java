package com.gmail.kirilllapitsky.todolist.dto;

import com.gmail.kirilllapitsky.todolist.entity.Scheduled;

import java.time.LocalDate;

public class ScheduledEventDto {
    public Scheduled scheduled;
    public LocalDate date;

    public ScheduledEventDto() {
    }

    public ScheduledEventDto(Scheduled scheduled, LocalDate date) {
        this.scheduled = scheduled;
        this.date = date;
    }
}
