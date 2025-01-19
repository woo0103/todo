package com.woo.todoo.controller;

import com.woo.todoo.domain.LoginForm;
import com.woo.todoo.domain.Member;
import com.woo.todoo.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    @GetMapping()
    public String loginForm(@ModelAttribute LoginForm loginForm) {
        return "login/loginForm";
    }

    @PostMapping()
    public String login(@ModelAttribute LoginForm loginForm, BindingResult bindingResult,  HttpServletRequest request) {
        String loginId = loginForm.getLoginId();
        String password = loginForm.getPassword();

        // 로그인에 실패하면 다시 로그인 창으로
        Member member = loginService.login(loginId, password);
        if (member == null) {
            bindingResult.reject("login", "아이디 또는 비밀번호가 일치하지 않습니다. 다시 입력해 주세요.");
            return "login/loginForm";
        }

        // 로그인 성공
        // 세션 생성
        // 세션이 있으면 있는 세션 반환, 없으면 생성
        HttpSession session = request.getSession();
        // 세션에 로그인 멤버 정보 저장
        session.setAttribute("loginMember", member);
        return "redirect:/";


    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
