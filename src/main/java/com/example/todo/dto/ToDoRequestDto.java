package com.example.todo.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class ToDoRequestDto {
    @NotBlank(message = "Title is required")
    private String title;
    private String description;
    @NotNull(message = "Completed status is required")
    private Boolean completed;
}
