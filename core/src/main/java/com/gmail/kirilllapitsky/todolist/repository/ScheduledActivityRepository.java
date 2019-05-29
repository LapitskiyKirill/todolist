package com.gmail.kirilllapitsky.todolist.repository;

import com.gmail.kirilllapitsky.todolist.entity.Scheduled;
import com.gmail.kirilllapitsky.todolist.entity.ScheduledActivity;
import com.gmail.kirilllapitsky.todolist.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduledActivityRepository extends CrudRepository<ScheduledActivity, Long> {
    List<ScheduledActivity> findAllByScheduled(Scheduled scheduled);
}
