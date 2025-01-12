package com.woo.todoo.controller;

import com.woo.todoo.domain.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String home(HttpServletRequest request) {
        // 세션에 로그인 멤버에 대한 정보가 있는지 찾아봄
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");

        // 세션에 로그인 멤버에 대한 정보가 없으면 홈으로
        if (loginMember == null) {
            log.info("HomeController: 로그인 멤버 없음");
            return "home";
        }

        // 세션에 로그인 멤버에 대한 정보가 있으면 todo로
        log.info("HomeController: 로그인 멤버 있음");
        return "redirect:/todoo";
    }
}

