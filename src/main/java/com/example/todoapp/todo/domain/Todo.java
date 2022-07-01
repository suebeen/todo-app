package com.example.todoapp.todo.domain;

import com.example.todoapp.common.BaseEntity;
import com.example.todoapp.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "is_deleted = false")
@Entity(name = "todo")
public class Todo extends BaseEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "done", nullable = false)
    private boolean done = false;   // boolean: 자료형이기 때문에 null값 넣을 수 없음

    @ManyToOne(fetch = FetchType.LAZY)  // 지연 로딩 사용으로 user객체를 매번 불러오지 x
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Builder
    public Todo(final User user, final String id, final String title, final boolean done) {
        this.user = user;
        this.id = id;
        this.title = title;
        this.done = done;
        user.getTodos().add(this);  // 해당 객체 생성시 user의 todoList에도 자동추가
    }

    public void update(final String title, final boolean done) {
        this.title = title;
        this.done = done;
    }
}
