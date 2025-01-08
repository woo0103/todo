package com.woo.todo.domain;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
// 로그인할때만 사용하는 클래스
public class LoginForm {
    private String loginId;
    private String password;

}
