package com.example.todoapp.todo.dto;

import com.example.todoapp.common.UUID;
import com.example.todoapp.todo.domain.Todo;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoDTO {

    @UUID(message = "Id must be in UUID format.")
    private String id;

    @NotBlank(message = "Title must not be blank.")
    private String title;

    @NotNull(message = "Done must not be null.")
    private boolean done;

}
