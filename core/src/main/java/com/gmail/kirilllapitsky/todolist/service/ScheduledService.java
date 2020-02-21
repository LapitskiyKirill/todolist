package com.gmail.kirilllapitsky.todolist.service;

import com.gmail.kirilllapitsky.todolist.dto.DateRangeDto;
import com.gmail.kirilllapitsky.todolist.dto.EditScheduledDto;
import com.gmail.kirilllapitsky.todolist.dto.NewScheduledDto;
import com.gmail.kirilllapitsky.todolist.dto.ScheduledEventDto;
import com.gmail.kirilllapitsky.todolist.entity.Scheduled;
import com.gmail.kirilllapitsky.todolist.entity.ScheduledActivity;
import com.gmail.kirilllapitsky.todolist.entity.Task;
import com.gmail.kirilllapitsky.todolist.entity.User;
import com.gmail.kirilllapitsky.todolist.exception.AuthenticationException;
import com.gmail.kirilllapitsky.todolist.exception.NoSuchEntityException;
import com.gmail.kirilllapitsky.todolist.repository.ScheduledActivityRepository;
import com.gmail.kirilllapitsky.todolist.repository.ScheduledRepository;
import com.gmail.kirilllapitsky.todolist.repository.TaskRepository;
import com.gmail.kirilllapitsky.todolist.util.Dates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScheduledService {

    private final ScheduledRepository scheduledRepository;

    private final TaskRepository taskRepository;

    private final ScheduledActivityRepository scheduledActivityRepository;

    @Autowired
    public ScheduledService(ScheduledRepository scheduledRepository, TaskRepository taskRepository, ScheduledActivityRepository scheduledActivityRepository) {
        this.scheduledRepository = scheduledRepository;
        this.taskRepository = taskRepository;
        this.scheduledActivityRepository = scheduledActivityRepository;
    }

    public void delete(User user, Long taskId)
            throws NoSuchEntityException, AuthenticationException {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(NoSuchEntityException::new);
        Scheduled scheduled = scheduledRepository.findByTask(task)
                .orElseThrow(NoSuchEntityException::new);
        if (task.getUser().getId().equals(user.getId())) {
            scheduled.setDeleted(true);
            scheduledRepository.save(scheduled);
        } else
            throw new AuthenticationException();

    }

    public Scheduled create(User user, NewScheduledDto newScheduledDto) throws NoSuchEntityException, AuthenticationException {

        Task task = taskRepository.findById(newScheduledDto.taskId)
                .orElseThrow(NoSuchEntityException::new);
        if (!task.getUser().getId().equals(user.getId()))
            throw new AuthenticationException();
        Scheduled scheduled = new Scheduled(task, newScheduledDto.from, newScheduledDto.periodicity);

        scheduledRepository.save(scheduled);
        if (scheduled.getFrom().toLocalDate().equals(LocalDate.now())) {
            scheduledActivityRepository.save(new ScheduledActivity(scheduled, null, LocalDateTime.now()));
        }

        scheduled.setDeleted(false);
        return scheduled;
    }

    public Scheduled edit(User user, EditScheduledDto editScheduledDto)
            throws AuthenticationException, NoSuchEntityException {

        Task task = taskRepository.findById(editScheduledDto.taskId)
                .orElseThrow(NoSuchEntityException::new);

        if (!task.getUser().getId().equals(user.getId()))
            throw new AuthenticationException();

        Scheduled scheduled = scheduledRepository.findByTask(task)
                .orElseThrow(NoSuchEntityException::new);

        scheduled.setFrom(editScheduledDto.from);
        scheduled.setPeriodicity(editScheduledDto.periodicity);
        scheduled.setDeleted(false);
        scheduledRepository.save(scheduled);
        return scheduled;
    }

    public List<Scheduled> getAll(User user) {
        return user.getTasks().stream()
                .map(Task::getScheduled)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

    }

    public List<ScheduledEventDto> getEvents(User user, Long taskId, DateRangeDto dateRangeDto)
            throws NoSuchEntityException, AuthenticationException {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(NoSuchEntityException::new);
        if (!task.getUser().getId().equals(user.getId()))
            throw new AuthenticationException();

        Scheduled scheduled = scheduledRepository.findById(taskId).orElseThrow(() -> new NoSuchEntityException("No such scheduled task"));

        return getScheduledScheduledEventsInRange(scheduled, dateRangeDto);
    }

    public List<ScheduledEventDto> getScheduledScheduledEventsInRange(Scheduled scheduled, DateRangeDto dateRangeDto) {

        return generateScheduledDatesInRange(scheduled, dateRangeDto).stream()
                .map(d -> new ScheduledEventDto(scheduled, d))
                .collect(Collectors.toList());
    }

    private List<LocalDate> generateScheduledDatesInRange(Scheduled scheduled, DateRangeDto dateRangeDto) {
        if (dateRangeDto.to.isBefore(scheduled.getFrom().toLocalDate()))
            return Collections.emptyList();

        List<LocalDate> localDates = new ArrayList<>();
        LocalDate currentDate = scheduled.getFrom().toLocalDate();
        while (true) {
            if (Dates.in(currentDate, dateRangeDto)) {
                localDates.add(currentDate);
            }
            Optional<LocalDate> currentDateOptional = Dates.next(currentDate, scheduled.getPeriodicity());
            if (currentDateOptional.isPresent()) {
                currentDate = currentDateOptional.get();
            } else {
                break;
            }
        }

        return localDates;
    }
}
