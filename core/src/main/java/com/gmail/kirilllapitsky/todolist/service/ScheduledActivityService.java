package com.gmail.kirilllapitsky.todolist.service;

import com.gmail.kirilllapitsky.todolist.dto.ActivityDto;
import com.gmail.kirilllapitsky.todolist.dto.DateRangeDto;
import com.gmail.kirilllapitsky.todolist.entity.ScheduledActivity;
import com.gmail.kirilllapitsky.todolist.entity.Task;
import com.gmail.kirilllapitsky.todolist.entity.User;
import com.gmail.kirilllapitsky.todolist.repository.ScheduledActivityRepository;
import com.gmail.kirilllapitsky.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @Transactional
    @Scheduled(cron = "0 0 0 * * ?")
    public void generateTodayScheduledActivities() {
        userRepository.findAll().stream()
                .parallel()
                .forEach(u -> {
                    scheduledService.getAll(u).stream()
                            .parallel()
                            .forEach(s -> {
                                scheduledService.getScheduledScheduledEventsInRange(s, DateRangeDto.dayOf(LocalDate.now())).stream()
                                        .parallel()
                                        .map(e -> new ScheduledActivity(e.scheduled, null, LocalDateTime.now()))
                                        .forEach(scheduledActivityRepository::save);
                            });
                });
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

    public List<ActivityDto> getTodays(User user) {

        List<com.gmail.kirilllapitsky.todolist.entity.Scheduled> scheduleds = user.getTasks().stream()
                .map(Task::getScheduled)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        List<ScheduledActivity> activities = new LinkedList<ScheduledActivity>(Collections.emptyList());
        scheduleds
                .forEach(scheduled -> {
                    activities.addAll(scheduledActivityRepository.findAllByScheduled(scheduled));
                });
        List<ActivityDto> activitiesDto = new java.util.ArrayList<>(Collections.emptyList());
        activities
                .stream()
                .filter(a -> a.getDate()
                        .toLocalDate()
                        .equals(LocalDate.now()))
                .forEach(scheduledActivity -> {
                    activitiesDto.add(new ActivityDto(scheduledActivity.getId(),
                            scheduledActivity.getScheduled().getId(),
                            scheduledActivity.getComplete(),
                            scheduledActivity.getDate()));
                });
        return activitiesDto;

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
