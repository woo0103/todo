package com.woo.todoo.service;

import com.woo.todoo.domain.Todoo;
import com.woo.todoo.repository.TodoMemoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoMemoryService {

    private final TodoMemoryRepository repository;

    //저장
    public Long addTodo(Todoo todo) {
        return repository.save(todo);
    }

    //조회
    public Todoo findTodo(Long id) {
        return repository.find(id);
    }

    //수정
    public void updateTodo(Long id, String title, String description) {
        repository.update(id, title, description);
    }

    //삭제
    public void removeTodo(Long id) {
        repository.delete(id);
    }

    //전제 조회
    public List<Todoo> todoList() {
        return repository.findAll();
    }

    //전체 삭제
    public void removeAll() {
        repository.clear();
    }
}
