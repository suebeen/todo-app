package com.example.todoapp.todo.domain;

import com.example.todoapp.todo.dto.request.TodoCreateRequestDto;
import com.example.todoapp.todo.dto.response.TodoDetailResponseDto;
import com.example.todoapp.user.domain.User;
import lombok.RequiredArgsConstructor;

import javax.persistence.Converter;

@RequiredArgsConstructor
@Converter
public class TodoConverter {

    public Todo toEntity(final User user, final TodoCreateRequestDto dto) {
        return Todo.builder()
                .user(user)
                .id(dto.getId())
                .title(dto.getTitle())
                .build();
    }

    public TodoDetailResponseDto toTodoDetailResponseDto(final Todo todo) {
        return TodoDetailResponseDto.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .done(todo.isDone())
                .build();
    }

}
