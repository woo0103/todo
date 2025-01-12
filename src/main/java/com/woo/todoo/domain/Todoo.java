package com.woo.todoo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Todoo {
    public Todoo() {
    }

    @Id
    @GeneratedValue
    @Column(name = "TODO_ID")
    private Long id;

    private String title;
    private String description;
    private boolean completed = false;

    // 멤버랑 다대일 연관관계
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public Todoo(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
