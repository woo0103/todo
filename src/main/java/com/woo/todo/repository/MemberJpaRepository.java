package com.woo.todo.repository;

import com.woo.todo.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberJpaRepository {

    private final EntityManager em;

    // 저장
    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    // id로 조회
    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    // 로그인id로 조회
    public Member findByLoginId(String loginId) {
        return em.find(Member.class, loginId);
    }

    // 전체 Member 조회
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    // 삭제
    public void delete(Long id) {
        Member deleteMember = em.find(Member.class, id);
        em.remove(deleteMember);
    }

    // 전체 삭제
    public void clear() {
        em.createQuery("delete from Member m", Member.class);
    }


}
