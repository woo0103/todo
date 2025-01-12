package com.woo.todoo.repository;

import com.woo.todoo.domain.Member;
import com.woo.todoo.domain.Todoo;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class TodoJpaRepository {

    private final EntityManager em;

    // 저장
    // member랑 todo연결
    public Long save(Todoo todo, Member member) {
        todo.setMember(member);
        em.persist(todo);
        return todo.getId();
    }

    //조회
    public Todoo find(Long id) {
        return em.find(Todoo.class, id);
    }

    //수정
    public void update(Long id, String title, String description) {
        log.info("id={}", id);
        Todoo findTodo = em.find(Todoo.class, id);
        findTodo.setTitle(title);
        findTodo.setDescription(description);

    }

    // completed 값 변경
    // O, X 전환
    public void checked(Long id) {
        Todoo findTodo = em.find(Todoo.class, id);
        findTodo.setCompleted(!findTodo.isCompleted());
    }

    //삭제
    public void delete(Long id) {
        Todoo deleteTodo = em.find(Todoo.class, id);
        em.remove(deleteTodo);
    }

    //전제 조회
    public List<Todoo> findAll(Member member) {
        return em.createQuery("select t from Todoo t where t.member = :member", Todoo.class)
                .setParameter("member", member)
                .getResultList();

    }

    // 멤버가 작성한 todo 전체 삭제
    public void clear(Member member) {
        em.createQuery("delete from Todoo t where t.member = :member")
                .setParameter("member", member)
                .executeUpdate();
    }

}
