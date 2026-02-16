package com.example.todo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Todos")
public class ToDoModel {
    @Id
    private String id;
    private String title;
    private String description;
    private boolean completed;
}
