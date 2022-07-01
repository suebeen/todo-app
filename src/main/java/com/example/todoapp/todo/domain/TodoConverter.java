package com.example.todoapp.todo.domain;

import com.example.todoapp.todo.dto.TodoDTO;
import com.example.todoapp.user.domain.User;
import lombok.RequiredArgsConstructor;

import javax.persistence.Converter;

@RequiredArgsConstructor
@Converter
public class TodoConverter {

    public Todo toEntity(final User user, final TodoDTO dto) {
        return Todo.builder()
                .user(user)
                .id(dto.getId())
                .title(dto.getTitle())
                .done(dto.isDone())
                .build();
    }

    public TodoDTO toTodoDTO(final Todo todo) {
        return TodoDTO.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .done(todo.isDone())
                .build();
    }

}
