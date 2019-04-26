package com.gmail.kirilllapitsky.todolist.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class DateRangeDto {
    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate from;
    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate to;

    public DateRangeDto() {
    }

    public DateRangeDto(LocalDate from, LocalDate to) {
        this.from = from;
        this.to = to;
    }

    public static DateRangeDto dayOf(LocalDate date) {
        return new DateRangeDto(date, date);
    }
}
