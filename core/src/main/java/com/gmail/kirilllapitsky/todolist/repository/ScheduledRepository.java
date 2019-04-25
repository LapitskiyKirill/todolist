package com.gmail.kirilllapitsky.todolist.repository;

import com.gmail.kirilllapitsky.todolist.entity.Scheduled;
import com.gmail.kirilllapitsky.todolist.entity.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheduledRepository extends CrudRepository<Scheduled, Long> {
    Optional<Scheduled> findByTask(Task task);
}