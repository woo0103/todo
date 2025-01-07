package com.woo.todo.repository;

import com.woo.todo.domain.Todo;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class TodoJpaRepository {

    private final EntityManager em;

    public Long save(Todo todo) {
        em.persist(todo);
        return todo.getId();
    }

    //조회
    public Todo find(Long id) {
        return em.find(Todo.class, id);
    }

    //수정
    public void update(Long id, String title, String description) {
        log.info("id={}", id);
        Todo findTodo = em.find(Todo.class, id);
        findTodo.setTitle(title);
        findTodo.setDescription(description);

    }

    // completed 값 변경
    // O, X 전환
    public void checked(Long id) {
        Todo findTodo = em.find(Todo.class, id);
        findTodo.setCompleted(!findTodo.isCompleted());
    }

    //삭제
    public void delete(Long id) {
        Todo deleteTodo = em.find(Todo.class, id);
        em.remove(deleteTodo);
    }

    //전제 조회
    public List<Todo> findAll() {
        return em.createQuery("select t from Todo t", Todo.class)
                .getResultList();

    }

    //전체 삭제
    public void clear() {
        em.createQuery("delete from Todo").executeUpdate();
    }

}
