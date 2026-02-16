package com.example.todo.repository;

import com.example.todo.model.ToDoModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToDoRepository extends MongoRepository<ToDoModel, String> {
}
