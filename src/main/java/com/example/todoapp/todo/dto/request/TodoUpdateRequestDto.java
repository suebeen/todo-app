package com.example.todoapp.todo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoUpdateRequestDto {

    @NotBlank(message = "Title must not be blank.")
    private String title;

    @NotNull(message = "Done must not be null.")
    private boolean done;
}
