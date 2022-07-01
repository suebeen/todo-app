package com.example.todoapp.user.domain;

import com.example.todoapp.common.exception.EntityExceptionSuppliers;
import com.example.todoapp.todo.domain.Todo;
import com.example.todoapp.user.dto.request.UserUpdateRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Column(name = "permission")
    @Enumerated(value = EnumType.STRING)
    private Permission permission = Permission.ROLE_USER;

    @Builder
    public User(final String username, final String email, final String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void update(final PasswordEncoder passwordEncoder, final UserUpdateRequestDto request) {
        if (!request.getPassword().isEmpty()) {
            this.password = passwordEncoder.encode(request.getPassword());
        }
        this.username = request.getUsername();
    }

    public void setPermission(final Permission permission) {
        this.permission = permission;
    }

    public boolean checkPassword(final PasswordEncoder passwordEncoder, final String password) throws Throwable {
        if (Objects.isNull(password)) {
            throw (Throwable) EntityExceptionSuppliers.incorrectPassword;
        }
        if (!passwordEncoder.matches(password, password)) {
            throw (Throwable) EntityExceptionSuppliers.incorrectPassword;
        } else {
            return true;
        }
    }
}