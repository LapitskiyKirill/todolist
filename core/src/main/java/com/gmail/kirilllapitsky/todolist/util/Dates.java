package com.gmail.kirilllapitsky.todolist.util;

import com.gmail.kirilllapitsky.todolist.dto.DateRangeDto;
import com.gmail.kirilllapitsky.todolist.entity.Scheduled;
import com.gmail.kirilllapitsky.todolist.entity.enums.Periodicity;

import java.time.LocalDate;
import java.util.Optional;

public class Dates {
    public static boolean in(LocalDate localDate, DateRangeDto dateRangeDto) {
        return localDate.isAfter(dateRangeDto.from) && localDate.isBefore(dateRangeDto.to);
    }
    public static Optional<LocalDate> next(LocalDate date, Periodicity periodicity) {
        switch (periodicity) {
            case DAILY:
                return Optional.of(date.plusDays(1));
            case WEEKLY:
                return Optional.of(date.plusWeeks(1));
            case MONTHLY:
                return Optional.of(date.plusMonths(1));
            case ANNUALLY:
                return Optional.of(date.plusYears(1));
            case ONCE:
            default:
                return Optional.empty();
        }
    }
}
