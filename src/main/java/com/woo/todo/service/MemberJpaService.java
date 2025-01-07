package com.woo.todo.service;

import com.woo.todo.domain.Member;
import com.woo.todo.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberJpaService {

    private final MemberJpaRepository repository;

    // 저장
    @Transactional
    public Long addMember(Member member) {
        return repository.save(member);
    }

    // id로 조회
    public Member findMemberById(Long id) {
        return repository.findById(id);
    }

    // 로그인id로 조회
    public Member findMemberByLoginId(String loginId) {
        return repository.findByLoginId(loginId);
    }

    // 전체 Member 조회
    public List<Member> findMembers() {
        return repository.findAll();
    }

    // 삭제
    @Transactional
    public void deleteMember(Long id) {
        repository.delete(id);
    }

    // 전체 삭제
    @Transactional
    public void removeAll() {
        repository.clear();
    }

}
