package com.woo.todoo.controller;

import com.woo.todoo.domain.Member;
import com.woo.todoo.service.MemberJpaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberController {

    private final MemberJpaService service;

    // 회원가입
    @GetMapping("/addForm")
    public String addForm(@ModelAttribute Member member, Model model) {
        log.info("addForm");
        model.addAttribute(member);
        return "addForm";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Member member) {
        log.info("add member={}", member);
        service.addMember(member);
        return "redirect:/";
    }
}
