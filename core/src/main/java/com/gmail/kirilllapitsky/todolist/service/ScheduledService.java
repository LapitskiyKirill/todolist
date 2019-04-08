package com.gmail.kirilllapitsky.todolist.service;

import com.gmail.kirilllapitsky.todolist.dto.NewScheduledDto;
import com.gmail.kirilllapitsky.todolist.dto.ScheduledDto;
import com.gmail.kirilllapitsky.todolist.entity.Scheduled;
import com.gmail.kirilllapitsky.todolist.entity.Task;
import com.gmail.kirilllapitsky.todolist.entity.User;
import com.gmail.kirilllapitsky.todolist.exception.AuthenticationException;
import com.gmail.kirilllapitsky.todolist.exception.NoSuchTaskException;
import com.gmail.kirilllapitsky.todolist.repository.ScheduledRepository;
import com.gmail.kirilllapitsky.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ScheduledService {

    @Autowired
    private ScheduledRepository scheduledRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AuthenticationService authenticationService;

    public void delete(String token, Long taskId) throws NoSuchTaskException, AuthenticationException {
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

    public Scheduled create(String token, NewScheduledDto newScheduledDto) throws NoSuchTaskException, AuthenticationException {
        User user = authenticationService.validate(token);
        Task task = taskRepository.findById(newScheduledDto.taskId)
                .orElseThrow(() -> new NoSuchTaskException());
        if (!task.getUser().getId().equals(user.getId()))
            throw new AuthenticationException();
        Scheduled scheduled = new Scheduled(task, newScheduledDto.from, newScheduledDto.periodicity, false);

        scheduledRepository.save(scheduled);
        return scheduled;
    }
}
