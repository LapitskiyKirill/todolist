package com.gmail.kirilllapitsky.todolist.dto;

public class CategoryDto {
    public Long id;
    public String value;

    public CategoryDto() {
    }

    public CategoryDto(Long id, String value) {
        this.id = id;
        this.value = value;
    }
}
