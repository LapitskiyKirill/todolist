package com.gmail.kirilllapitsky.todolist.service;

import com.gmail.kirilllapitsky.todolist.dto.EditScheduledDto;
import com.gmail.kirilllapitsky.todolist.dto.NewScheduledDto;
import com.gmail.kirilllapitsky.todolist.entity.Scheduled;
import com.gmail.kirilllapitsky.todolist.entity.Task;
import com.gmail.kirilllapitsky.todolist.entity.User;
import com.gmail.kirilllapitsky.todolist.exception.AuthenticationException;
import com.gmail.kirilllapitsky.todolist.exception.NoSuchTaskException;
import com.gmail.kirilllapitsky.todolist.repository.ScheduledRepository;
import com.gmail.kirilllapitsky.todolist.repository.TaskRepository;
import com.gmail.kirilllapitsky.todolist.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScheduledService {

    @Autowired
    private ScheduledRepository scheduledRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private TaskService taskService;

    public void delete(String token, Long taskId)
            throws NoSuchTaskException, AuthenticationException {
        User user = authenticationService.validate(token);
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchTaskException());
        Scheduled scheduled = scheduledRepository.findByTask(task)
                .orElseThrow(() -> new NoSuchTaskException());
        if (task.getUser().getId().equals(user.getId()))
            scheduledRepository.delete(scheduled);
        else
            throw new AuthenticationException();

    }

    public Scheduled create(String token, NewScheduledDto newScheduledDto)
            throws NoSuchTaskException, AuthenticationException {
        User user = authenticationService.validate(token);
        Task task = taskRepository.findById(newScheduledDto.taskId)
                .orElseThrow(() -> new NoSuchTaskException());
        if (!task.getUser().getId().equals(user.getId()))
            throw new AuthenticationException();
        Scheduled scheduled = new Scheduled(task, newScheduledDto.from, newScheduledDto.periodicity);

        scheduledRepository.save(scheduled);
        return scheduled;
    }

    public List<Scheduled> getScheduled(String token) {
        User user = authenticationService.validate(token);
        List<Task> tasks = taskService.all(user).stream()
                .map(t -> Mapper.map(t, Task.class))
                .collect(Collectors.toList());

        return tasks.stream()
                .map(t -> t.getScheduled())
                .filter(t -> t != null)
                .collect(Collectors.toList());
    }

    public Scheduled edit(String token, EditScheduledDto editScheduledDto)
            throws AuthenticationException, NoSuchTaskException {
        User user = authenticationService.validate(token);

        Task task = taskRepository.findById(editScheduledDto.taskId)
                .orElseThrow(() -> new NoSuchTaskException());

        if (!task.getUser().getId().equals(user.getId()))
            throw new AuthenticationException();

        Scheduled scheduled = scheduledRepository.findByTask(task)
                .orElseThrow(() -> new NoSuchTaskException());

        scheduled.setFrom(editScheduledDto.from);
        scheduled.setPeriodicity(editScheduledDto.periodicity);
        scheduledRepository.save(scheduled);
        return scheduled;
    }


}
