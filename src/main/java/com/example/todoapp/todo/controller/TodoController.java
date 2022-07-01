package com.example.todoapp.todo.controller;

import com.example.todoapp.todo.dto.request.TodoCreateRequestDto;
import com.example.todoapp.todo.dto.request.TodoUpdateRequestDto;
import com.example.todoapp.todo.dto.response.TodoDetailResponseDto;
import com.example.todoapp.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/todo")
@RestController
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<Void> createTodo(
            @Valid @RequestBody final TodoCreateRequestDto request
    ) {
        // @AuthenticationPrincipal final JwtAuthentication authentication 추가 예정
        final String userId = "";
        final String todoId = todoService.createTodo(userId, request);
        return ResponseEntity.created(URI.create("/api/v1/todo/" + todoId)).build();
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<Void> updateTodo(
            @PathVariable final String todoId,
            @Valid @RequestBody final TodoUpdateRequestDto request
    ) {
        todoService.updateTodo(todoId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void> deleteTodo(
            @PathVariable final String todoId
    ) {
        todoService.deleteTodo(todoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<TodoDetailResponseDto>> findAllTodoByUser(
    ) {
        // @AuthenticationPrincipal final JwtAuthentication authentication 추가 예정
        final String userId = "";
        return ResponseEntity.ok().body(todoService.findAllTodoByUser(userId));
    }

}