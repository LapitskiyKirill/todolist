package com.gmail.kirilllapitsky.todolist;

import com.gmail.kirilllapitsky.todolist.controller.AuthenticationController;
import com.gmail.kirilllapitsky.todolist.controller.RegisterController;
import com.gmail.kirilllapitsky.todolist.controller.TaskController;
import com.gmail.kirilllapitsky.todolist.dto.*;
import com.gmail.kirilllapitsky.todolist.exception.AuthenticationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class TaskTest {

    @Autowired
    private AuthenticationController authenticationController;

    @Autowired
    private RegisterController registerController;

    @Autowired
    private TaskController taskController;

    @Test
    public void shouldCreateTask() throws AuthenticationException, InterruptedException {
        registerController.register(new RegisterUserDto("nikita", "password"));
        TokenDto tokenDto = authenticationController.authenticate(new AuthenticationUserDto("nikita", "password"));
        TaskDto task = taskController.create(tokenDto.token, new NewTaskDto("newtext"));
        Assert.assertTrue(taskController.getAll(tokenDto.token).stream().anyMatch(t -> t.id.equals(task.id)));
    }
}
