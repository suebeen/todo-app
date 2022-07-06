package com.example.todoapp.user.domain;

import com.example.todoapp.common.BaseEntity;
import com.example.todoapp.todo.domain.Todo;
import com.example.todoapp.user.dto.request.UserUpdateRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE users SET is_deleted = true WHERE user_id = ?")
@Entity(name = "users")
public class User extends BaseEntity {
    /*
     * JPA 에서 객체의 수정은 insert-update-delete 순서
     * 변경된 자식 객체 insert, 기존의 자식 객체를 NULL 처리
     * orphanRemoval = true 로 하면 기존 NULL 처리한 자식을 DELETE
     * 즉, PK(JoinColumn) 값이 NULL 인 고아객체를 삭제해주는 역할
     */
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private final List<Todo> todos = new ArrayList<>();

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
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

    public void checkPassword(final PasswordEncoder passwordEncoder, final String password) {
        if (Objects.isNull(password)) {
            throw new IllegalArgumentException("Password do not match.");
        }
        if (!passwordEncoder.matches(password, this.password)) {
            throw new IllegalArgumentException("Password do not match.");
        }
    }
}