package com.woo.todo.service;

import com.woo.todo.domain.Todo;
import com.woo.todo.repository.TodoJpaRepository;
import com.woo.todo.repository.TodoMemoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoJpaService {

    private final TodoJpaRepository repository;

    //저장
    @Transactional
    public Long addTodo(Todo todo) {
        return repository.save(todo);
    }

    //조회
    public Todo findTodo(Long id) {
        return repository.find(id);
    }

    //수정
    @Transactional
    public void updateTodo(Long id, String title, String description) {
        repository.update(id, title, description);
    }

    //삭제
    @Transactional
    public void removeTodo(Long id) {
        repository.delete(id);
    }

    //전제 조회
    public List<Todo> todoList() {
        return repository.findAll();
    }

    //전체 삭제
    @Transactional
    public void removeAll() {
        repository.clear();
    }
}
