package com.woo.todoo.repository;

import com.woo.todoo.domain.Todoo;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class TodoMemoryRepository {

    private final Map<Long, Todoo> store = new HashMap<>();
    private Long sequence = 0L;

    //저장
    public Long save(Todoo todo) {
        todo.setId(++sequence);
        store.put(todo.getId(), todo);
        return todo.getId();
    }

    //조회
    public Todoo find(Long id) {
        return store.get(id);
    }

    //수정
    public void update(Long id, String title, String description) {
        log.info("id={}", id);
        Todoo findTodo = store.get(id);
        findTodo.setTitle(title);
        findTodo.setDescription(description);

    }

    //삭제
    public void delete(Long id) {
        store.remove(id);
    }

    //전제 조회
    public List<Todoo> findAll() {
        return new ArrayList<>(store.values());
    }

    //전체 삭제
    public void clear() {
        store.clear();
    }

    @PostConstruct
    public void init() {
        save(new Todoo("운동", "운동하자"));
        save(new Todoo("공부", "공부좀해라"));
    }

}
