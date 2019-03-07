package com.gmail.kirilllapitsky.todolist.entity;

import com.gmail.kirilllapitsky.todolist.dto.TaskDto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Mapper {

    public static TaskDto map(Task task) {
        return new TaskDto(
                task.getId(),
                task.getCategory().getValue(),
                task.getText(),
                task.getDeadline(),
                task.isCompleted(),
                task.getSubtasks()
                        .stream()
                        .map(d -> new TaskDto(
                                d.getId(),
                                d.getCategory().getValue(),
                                d.getText(),
                                d.getDeadline(),
                                d.isCompleted(),
                                Collections.emptyList()))
                        .collect(Collectors.toList()));
    }

    public static List<TaskDto> mapAll(List<Task> list) {
        return list
                .stream()
                .map(Mapper::map)
                .collect(Collectors.toList());
    }

}
