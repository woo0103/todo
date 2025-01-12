package com.woo.todoo.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
// 로그인할때 필요한 정보만 가지고 있는 클래스임
public class LoginForm {
    private String loginId;
    private String password;

}
