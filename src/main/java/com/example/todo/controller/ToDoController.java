package com.example.todo.controller;

import com.example.todo.dto.ToDoRequestDto;
import com.example.todo.dto.ToDoResponseDto;
import com.example.todo.service.ToDoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/todos")
public class ToDoController {
    private final ToDoService service;
    public ToDoController(ToDoService service){
        this.service = service;
    }

    // Create
    @PostMapping
    public ToDoResponseDto createTodo(
            @Valid @RequestBody ToDoRequestDto dto){
        return service.createTodo(dto);
    }

    // Read
    @GetMapping("/{id}")
    public ToDoResponseDto getTodoById(@PathVariable String id){
        return service.getTodoById(id);
    }

    // Update
    @PutMapping("/{id}")
    public ToDoResponseDto updateTodo(
            @PathVariable String id,
            @Valid @RequestBody ToDoRequestDto dto){
        return service.updateTodo(id, dto);
    }

    // Delete
    @DeleteMapping("/{id}")
    public String deleteTodo(@PathVariable String id){
        service.deleteTodo(id);
        return "Todo deleted successfully";
    }
}
