package com.example.todoapp.user.domain;

import com.example.todoapp.todo.domain.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
public class User {
    /*
     * JPA 에서 객체의 수정은 insert-update-delete 순서
     * 변경된 자식 객체 insert, 기존의 자식 객체를 NULL 처리
     * orphanRemoval = true 로 하면 기존 NULL 처리한 자식을 DELETE
     * 즉, PK(JoinColumn) 값이 NULL 인 고아객체를 삭제해주는 역할
     */
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private final List<Todo> todos = new ArrayList<>();

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // insert query 실행되는 시점에 id setting
    private String userId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password") // nullable = true : 소셜 로그인 사용 가능
    private String password;

    @Builder
    public User(final String username, final String email, final String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}