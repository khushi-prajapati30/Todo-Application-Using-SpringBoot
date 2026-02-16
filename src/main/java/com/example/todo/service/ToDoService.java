package com.example.todo.service;

import com.example.todo.dto.ToDoRequestDto;
import com.example.todo.dto.ToDoResponseDto;
import com.example.todo.model.ToDoModel;
import com.example.todo.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ToDoService {
    private final ToDoRepository repository;
    public ToDoService(ToDoRepository repository) {
        this.repository = repository;
    }
    // Create
    public ToDoResponseDto createTodo(ToDoRequestDto dto) {
        ToDoModel todo = new ToDoModel();
        todo.setTitle(dto.getTitle());
        todo.setDescription(dto.getDescription());
        todo.setCompleted(dto.getCompleted());
        ToDoModel saved = repository.save(todo);
        return new ToDoResponseDto(
                saved.getId(),
                saved.getTitle(),
                saved.getDescription(),
                saved.isCompleted()
        );
    }

    // Read
    public ToDoResponseDto getTodoById(String id) {
        ToDoModel todo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
        return new ToDoResponseDto(
                todo.getId(),
                todo.getTitle(),
                todo.getDescription(),
                todo.isCompleted()
        );
    }

    // Update
    public ToDoResponseDto updateTodo(String id, ToDoRequestDto dto) {
        ToDoModel existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());
        existing.setCompleted(dto.getCompleted());
        ToDoModel saved = repository.save(existing);
        return new ToDoResponseDto(
                saved.getId(),
                saved.getTitle(),
                saved.getDescription(),
                saved.isCompleted()
        );
    }

    // Delete
    public void deleteTodo(String id) {
        ToDoModel todo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot delete: Todo not found with id: " + id));
        repository.delete(todo);
    }
}
