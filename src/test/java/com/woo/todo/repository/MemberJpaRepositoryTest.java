package com.woo.todo.repository;

import com.woo.todo.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;



@Transactional
@SpringBootTest
class MemberJpaRepositoryTest {

    @Autowired
    private MemberJpaRepository repository;



    @Test
    void 아이디로_찾기() {
        Member member = new Member();
        member.setName("test");
        member.setPassword("test!");

        repository.save(member);
        Member findMember = repository.findById(member.getId());

        Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());
        Assertions.assertThat(findMember.getLoginId()).isEqualTo(member.getLoginId());
        Assertions.assertThat(findMember.getPassword()).isEqualTo(member.getPassword());
    }

    @Test
    void 삭제() {
        Member member = new Member();
        member.setName("test");
        member.setPassword("test!");

        repository.save(member);

        Long id = member.getId();

        repository.delete(id);

        Member findDeleteMember = repository.findById(id);
        Assertions.assertThat(findDeleteMember).isEqualTo(null);
    }

}