package com.example.todoapp.todo.dto.request;

import com.example.todoapp.common.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoCreateRequestDto {

    @UUID(message = "Id must be in UUID format.")
    private String id;

    @NotBlank(message = "Title must not be blank.")
    private String title;

}
