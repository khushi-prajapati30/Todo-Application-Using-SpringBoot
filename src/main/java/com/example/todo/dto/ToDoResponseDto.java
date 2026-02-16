package com.example.todo.dto;

public record ToDoResponseDto(
        String id,
        String title,
        String description,
        boolean completed
) {
}
