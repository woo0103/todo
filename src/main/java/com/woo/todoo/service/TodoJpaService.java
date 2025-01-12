package com.woo.todoo.service;

import com.woo.todoo.domain.Member;
import com.woo.todoo.domain.Todoo;
import com.woo.todoo.repository.TodoJpaRepository;
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
    public Long addTodo(Todoo todo, Member member) {
        return repository.save(todo, member);
    }

    //조회
    public Todoo findTodo(Long id) {
        return repository.find(id);
    }

    //수정
    @Transactional
    public void updateTodo(Long id, String title, String description) {
        repository.update(id, title, description);
    }

    // completed 값 변경
    // O, X 전환
    @Transactional
    public void completedChecked(Long id) {
        repository.checked(id);

    }

    //삭제
    @Transactional
    public void removeTodo(Long id) {
        repository.delete(id);
    }

    //전제 조회
    public List<Todoo> todoList(Member member) {
        return repository.findAll(member);
    }

    //전체 삭제
    @Transactional
    public void removeAll(Member member) {
        repository.clear(member);
    }
}
