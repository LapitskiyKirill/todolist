package com.gmail.kirilllapitsky.todolist.service;

import com.gmail.kirilllapitsky.todolist.dto.DateRangeDto;
import com.gmail.kirilllapitsky.todolist.entity.ScheduledActivity;
import com.gmail.kirilllapitsky.todolist.entity.User;
import com.gmail.kirilllapitsky.todolist.repository.ScheduledActivityRepository;
import com.gmail.kirilllapitsky.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class ScheduledActivityService {

    private final UserRepository userRepository;

    private final ScheduledService scheduledService;

    private final ScheduledActivityRepository scheduledActivityRepository;

    @Autowired
    public ScheduledActivityService(UserRepository userRepository, ScheduledService scheduledService, ScheduledActivityRepository scheduledActivityRepository) {
        this.userRepository = userRepository;
        this.scheduledService = scheduledService;
        this.scheduledActivityRepository = scheduledActivityRepository;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void generateTodayScheduledActivities() {
        userRepository.findAll().stream()
                .parallel()
                .forEach(u -> scheduledService.getAll(u).stream()
                        .parallel()
                        .forEach(s -> scheduledService.getScheduledScheduledEventsInRange(s, DateRangeDto.dayOf(LocalDate.now())).stream()
                                .parallel()
                                .map(e -> new ScheduledActivity(e.scheduled, null, LocalDateTime.now()))
                                .forEach(scheduledActivityRepository::save)));
    }

    public void complete(User user, Long scheduledActivityId) {
        ScheduledActivity scheduledActivity;
        if (scheduledActivityRepository.findById(scheduledActivityId).isPresent()) {
            scheduledActivity = scheduledActivityRepository.findById(scheduledActivityId).get();
            if (scheduledActivity.getScheduled().getTask().getUser().getId().equals(user.getId())) {
                if (scheduledActivity.getDate().toLocalDate().equals(LocalDateTime.now().toLocalDate())) {
                    scheduledActivity.setComplete(LocalDateTime.now());
                }
            }
            scheduledActivityRepository.save(scheduledActivity);
        }

    }

    public void uncheck(User user, Long scheduledActivityId) {
        ScheduledActivity scheduledActivity;
        if (scheduledActivityRepository.findById(scheduledActivityId).isPresent()) {
            scheduledActivity = scheduledActivityRepository.findById(scheduledActivityId).get();
            if (scheduledActivity.getScheduled().getTask().getUser().getId().equals(user.getId())) {
                if (scheduledActivity.getDate().toLocalDate().equals(LocalDateTime.now().toLocalDate())) {
                    scheduledActivity.setComplete(null);
                }
            }
            scheduledActivityRepository.save(scheduledActivity);
        }
    }
}
