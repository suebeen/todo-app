package com.example.todoapp.todo.domain;

import com.example.todoapp.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, String> {
    List<Todo> findByUser(User user);
}
