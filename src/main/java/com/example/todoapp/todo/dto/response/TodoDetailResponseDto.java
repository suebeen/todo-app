package com.example.todoapp.todo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoDetailResponseDto {

    private String id;

    private String title;

    private boolean done;
}
