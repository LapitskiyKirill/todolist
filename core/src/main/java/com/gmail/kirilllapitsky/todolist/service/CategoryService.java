package com.gmail.kirilllapitsky.todolist.service;

import com.gmail.kirilllapitsky.todolist.dto.CategoryDto;
import com.gmail.kirilllapitsky.todolist.dto.NewCategoryDto;
import com.gmail.kirilllapitsky.todolist.dto.TaskDto;
import com.gmail.kirilllapitsky.todolist.entity.TaskCategory;
import com.gmail.kirilllapitsky.todolist.entity.User;
import com.gmail.kirilllapitsky.todolist.exception.NoSuchEntityException;
import com.gmail.kirilllapitsky.todolist.repository.TaskCategoryRepository;
import com.gmail.kirilllapitsky.todolist.repository.TaskRepository;
import com.gmail.kirilllapitsky.todolist.repository.UserRepository;
import com.gmail.kirilllapitsky.todolist.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final TaskCategoryRepository taskCategoryRepository;

    private final UserRepository userRepository;

    private final TaskRepository taskRepository;

    @Autowired
    public CategoryService(TaskCategoryRepository taskCategoryRepository, UserRepository userRepository, TaskRepository taskRepository) {
        this.taskCategoryRepository = taskCategoryRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public List<CategoryDto> getCategories(User user) {
        User admin = userRepository.findByLogin("admin");
        List<TaskCategory> categories = taskCategoryRepository.findAllByUser(user);
        categories.addAll(taskCategoryRepository.findAllByUser(admin));
        return Mapper.mapAll(categories, CategoryDto.class);
    }

    public void create(User user, NewCategoryDto newCategoryDto) {
        TaskCategory taskCategory = new TaskCategory(user, newCategoryDto.value);
        User admin = userRepository.findByLogin("admin");

        if (taskCategoryRepository.findByValueAndUser(newCategoryDto.value, admin).isPresent()) {
            taskCategory = taskCategoryRepository.findByValueAndUser(newCategoryDto.value, admin).get();
        } else if (newCategoryDto.value != null && taskCategoryRepository.findByValueAndUser(newCategoryDto.value, user).isPresent()) {
            Optional<TaskCategory> taskCategoryOptional = taskCategoryRepository.findByValueAndUser(newCategoryDto.value, user);
            taskCategory = taskCategoryOptional.orElseGet(() -> new TaskCategory(user, newCategoryDto.value));
        } else if (newCategoryDto.value != null) {
            taskCategory = new TaskCategory(user, newCategoryDto.value);
            taskCategoryRepository.save(taskCategory);
        }

        taskCategoryRepository.save(taskCategory);
    }

    public List<TaskDto> getTasks(User user, String category) throws NoSuchEntityException {
        User admin = userRepository.findByLogin("admin");
        TaskCategory taskCategory;
        if (taskCategoryRepository.findByValueAndUser(category, user).isPresent()) {
            taskCategory = taskCategoryRepository.findByValueAndUser(category, user).get();
        } else if (taskCategoryRepository.findByValueAndUser(category, admin).isPresent()) {
            taskCategory = taskCategoryRepository.findByValueAndUser(category, admin).get();
        } else {
            throw new NoSuchEntityException("no such category");
        }
        return Mapper.mapAll(taskRepository.findByCategoryAndUser(taskCategory, user).orElseThrow(NoSuchEntityException::new), TaskDto.class);
    }
}
