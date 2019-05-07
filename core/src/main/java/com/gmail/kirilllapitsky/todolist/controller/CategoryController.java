package com.gmail.kirilllapitsky.todolist.controller;

import com.gmail.kirilllapitsky.todolist.dto.CategoryDto;
import com.gmail.kirilllapitsky.todolist.dto.NewCategoryDto;
import com.gmail.kirilllapitsky.todolist.dto.TaskDto;
import com.gmail.kirilllapitsky.todolist.entity.User;
import com.gmail.kirilllapitsky.todolist.exception.NoSuchEntityException;
import com.gmail.kirilllapitsky.todolist.service.AuthenticationService;
import com.gmail.kirilllapitsky.todolist.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    private final CategoryService categoryService;

    private final AuthenticationService authenticationService;

    @Autowired
    public CategoryController(CategoryService categoryService, AuthenticationService authenticationService) {
        this.categoryService = categoryService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("createCategory")
    public void createCategory(@RequestHeader("token") String token, @RequestBody NewCategoryDto newCategoryDto) throws NoSuchEntityException {
        User user = authenticationService.validate(token);
        categoryService.create(user, newCategoryDto);
    }

    @GetMapping("getCategories")
    public List<CategoryDto> getCategories(@RequestHeader("token") String token) throws NoSuchEntityException {
        User user = authenticationService.validate(token);
        return categoryService.getCategories(user);
    }

    @GetMapping("getCategoryTasks")
    public List<TaskDto> getCategoryTasks(@RequestHeader("token") String token, @RequestHeader("category") String category) throws NoSuchEntityException {
        User user = authenticationService.validate(token);
        return categoryService.getTasks(user, category);
    }
}