package com.woo.todo.service;

import com.woo.todo.domain.Member;
import com.woo.todo.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final MemberJpaRepository repository;

    public Member login(String loginId, String password) {
        Member loginMember = repository.findByLoginId(loginId);

        // 아이디, 비밀번호가 일치하는 경우 멤버 반환
        // 일치하지 않는 경우 null 반환
        if (loginMember != null) {
            loginMember.getPassword().equals(password);
            log.info("LoginService: login success");
            return loginMember;
        } else {
            log.info("LoginService: login fail");
            return null;
        }
    }
}
