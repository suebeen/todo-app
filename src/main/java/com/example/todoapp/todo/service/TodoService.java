package com.example.todoapp.todo.service;

import com.example.todoapp.common.exception.EntityExceptionSuppliers;
import com.example.todoapp.todo.domain.Todo;
import com.example.todoapp.todo.domain.TodoConverter;
import com.example.todoapp.todo.domain.TodoRepository;
import com.example.todoapp.todo.dto.TodoDTO;
import com.example.todoapp.user.domain.User;
import com.example.todoapp.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    private final TodoConverter todoConverter;

    public String testService() {
        // TodoEntity 생성
        final Todo entity = Todo.builder().title("My first todo item").build();
        // TodoEntity 저장
        todoRepository.save(entity);
        // TodoEntity 검색
        final Todo savedEntity = todoRepository.findById(entity.getId()).get();
        return savedEntity.getTitle();
    }

    @Transactional
    public String createTodo(final String userId, final TodoDTO todoDto) {
        final User user = getUser(userId);
        final Todo todo = todoConverter.toEntity(user, todoDto);
        return todoRepository.save(todo).getId();
    }

    @Transactional
    public void updateTodo(final String id, final TodoDTO todoDTO) {
        final Todo todo = getTodo(id);
        todo.update(todoDTO.getTitle(), todoDTO.isDone());
    }

    @Transactional
    public void deleteTodo(final String id) {
        final Todo todo = getTodo(id);
        todoRepository.delete(todo);
    }

    public List<TodoDTO> findAllTodoByUser(final String userId) {
        final User user = getUser(userId);
        final List<Todo> todoList = todoRepository.findByUser(user);

        return todoList.stream()
                .map(todoConverter::toTodoDTO)
                .collect(Collectors.toList());
    }

    // user not found : exception 처리
    private User getUser(final String userId) {
        return userRepository.findById(userId)
                .orElseThrow(EntityExceptionSuppliers.userNotFound);
    }

    // 투두 not found : exception 처리
    private Todo getTodo(final String id) {
        return todoRepository.findById(id)
                .orElseThrow(EntityExceptionSuppliers.todoNotFound);
    }
}
