package com.gmail.kirilllapitsky.todolist.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gmail.kirilllapitsky.todolist.entity.enums.Periodicity;

import java.time.LocalDateTime;

public class EditScheduledDto {
    public Long taskId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    public LocalDateTime from;
    public Periodicity periodicity;

    public EditScheduledDto() {

    }

    public EditScheduledDto(Long taskId, LocalDateTime from, Periodicity periodicity) {
        this.taskId = taskId;
        this.from = from;
        this.periodicity = periodicity;
    }
}
