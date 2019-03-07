package com.gmail.kirilllapitsky.todolist;

import com.gmail.kirilllapitsky.todolist.dto.AuthenticationUserDto;
import com.gmail.kirilllapitsky.todolist.dto.NewTaskDto;
import com.gmail.kirilllapitsky.todolist.dto.RegisterUserDto;
import com.gmail.kirilllapitsky.todolist.dto.TokenDto;
import com.gmail.kirilllapitsky.todolist.entity.Task;
import com.gmail.kirilllapitsky.todolist.entity.User;
import com.gmail.kirilllapitsky.todolist.exception.AuthenticationException;
import com.gmail.kirilllapitsky.todolist.service.AuthenticationService;
import com.gmail.kirilllapitsky.todolist.service.RegisterService;
import com.gmail.kirilllapitsky.todolist.service.TaskService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class TaskTest {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private TaskService taskService;

    @Test
    public void shouldCreateTask() throws AuthenticationException, InterruptedException {
        registerService.register(new RegisterUserDto("nikita", "password"));
        TokenDto tokenDto = authenticationService.authenticate(new AuthenticationUserDto("nikita", "password"));
        User user = authenticationService.validate(tokenDto.token);
        Task task = taskService.create(user, new NewTaskDto("newtext"));

        Assert.assertTrue(taskService.all(user).stream().anyMatch(t -> t.id.equals(task.getId())));
    }

}
