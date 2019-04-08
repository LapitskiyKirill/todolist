package com.gmail.kirilllapitsky.todolist.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gmail.kirilllapitsky.todolist.entity.enums.Periodicity;

import java.time.LocalDateTime;

public class ScheduledDto {
    public Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime from;
    public Periodicity periodicity;
    public Boolean active;

    public ScheduledDto() {
    }

    public ScheduledDto(LocalDateTime from, Periodicity periodicity, Boolean active) {
        this.from = from;
        this.periodicity = periodicity;
        this.active = active;
    }
}
