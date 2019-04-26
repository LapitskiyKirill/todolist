package com.gmail.kirilllapitsky.todolist.service;

import com.gmail.kirilllapitsky.todolist.dto.CategoryDto;
import com.gmail.kirilllapitsky.todolist.dto.NewCategoryDto;
import com.gmail.kirilllapitsky.todolist.entity.TaskCategory;
import com.gmail.kirilllapitsky.todolist.entity.User;
import com.gmail.kirilllapitsky.todolist.repository.TaskCategoryRepository;
import com.gmail.kirilllapitsky.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final TaskCategoryRepository taskCategoryRepository;

    private final UserRepository userRepository;

    @Autowired
    public CategoryService(TaskCategoryRepository taskCategoryRepository, UserRepository userRepository) {
        this.taskCategoryRepository = taskCategoryRepository;
        this.userRepository = userRepository;
    }

    public List<CategoryDto> getCategories(User user) {
        User admin = userRepository.findByLogin("admin");
        List<TaskCategory> categories = taskCategoryRepository.findAllByUser(user);
        categories.addAll(taskCategoryRepository.findAllByUser(admin));
        return categories
                .stream()
                .map(t -> new CategoryDto(t.getId(), t.getValue()))
                .collect(Collectors.toList());
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
}
