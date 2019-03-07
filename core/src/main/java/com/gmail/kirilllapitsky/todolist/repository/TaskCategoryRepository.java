package com.gmail.kirilllapitsky.todolist.repository;

import com.gmail.kirilllapitsky.todolist.entity.TaskCategory;
import com.gmail.kirilllapitsky.todolist.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskCategoryRepository extends CrudRepository<TaskCategory, Long> {
    Optional<TaskCategory> findByValueAndUser(String value, User user);
}
