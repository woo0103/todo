package com.woo.todo.controller;

import com.woo.todo.domain.LoginForm;
import com.woo.todo.domain.Member;
import com.woo.todo.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.print.attribute.standard.PresentationDirection;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    @GetMapping()
    public String loginForm(@ModelAttribute LoginForm loginForm) {
        return "loginForm";
    }

    @PostMapping()
    public String login(@ModelAttribute LoginForm loginForm, HttpServletRequest request) {
        String loginId = loginForm.getLoginId();
        String password = loginForm.getPassword();

        // 로그인에 실패하면 다시 로그인 창으로
        Member member = loginService.login(loginId, password);
        if (member == null) {
            return "redirect:/login";
        }

        // 로그인 성공
        // 세션 생성
        // 세션이 있으면 있는 세션 반환, 없으면 생성
        HttpSession session = request.getSession();
        // 세션에 로그인 멤버 정보 저장
        session.setAttribute("loginMember", member);
        return "redirect:/";


    }
}
