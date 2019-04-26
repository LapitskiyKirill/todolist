package com.gmail.kirilllapitsky.todolist.repository;

import com.gmail.kirilllapitsky.todolist.entity.Task;
import com.gmail.kirilllapitsky.todolist.entity.TaskCategory;
import com.gmail.kirilllapitsky.todolist.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    Optional<List<Task>> findByCategoryAndUser(TaskCategory taskCategory, User user);
}
