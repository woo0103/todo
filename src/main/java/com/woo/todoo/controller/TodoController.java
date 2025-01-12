package com.woo.todoo.controller;

import com.woo.todoo.domain.Member;
import com.woo.todoo.domain.Todoo;
import com.woo.todoo.service.TodoJpaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/todoo")
@Slf4j
public class TodoController {

    private final TodoJpaService service;

    // 홈 화면
    // 전체 todoList 출력
    @GetMapping()
    public String home(Model model, HttpServletRequest request) {
        // 로그인 했는지 확인하려고 세션 가져옴
        Member loginMember = getLoginMember(request);
        // 로그인 안한 놈은 홈으로
        if (loginMember == null) {
            log.info("redirect:/");
            return "redirect:/";
        }

        // 로그인 한 놈은 todohome로 이동
        List<Todoo> todoos = service.todoList(loginMember);
        model.addAttribute("todoos", todoos);
        log.info("todoohome");
        return "todoo/todoohHome";
    }

    // todo 추가

    @PostMapping("/add")
    public String add(@RequestParam("title") String title, @RequestParam("description") String description, HttpServletRequest request) {
        Member loginMember = getLoginMember(request);
        Todoo todoo = new Todoo(title, description);
        service.addTodo(todoo, loginMember);
        log.info("addTodoo={}", todoo);
        return "redirect:/todoo";
    }
    // todo 1개 삭제

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        service.removeTodo(id);
        log.info("removeTodo");
        return "redirect:/todoo";
    }
    // todo 내용 수정

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, @RequestParam("title") String title, @RequestParam("description") String description) {
        service.updateTodo(id, title, description);
        log.info("updateTodoo");
        return "redirect:/todoo";
    }
    // completed 값 변경
    // O,X 전환
    // 로그 안찍음

    @PostMapping("/checked/{id}")
    public String checked(@PathVariable("id") Long id) {
        service.completedChecked(id);;
        return "redirect:/todoo";
    }
    @PostMapping("/clear")
    public String clear(HttpServletRequest request) {
        Member loginMember = getLoginMember(request);
        service.removeAll(loginMember);
        log.info("todoClear");
        return "redirect:/todoo";
    }

    private static Member getLoginMember(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (Member) session.getAttribute("loginMember");
    }
}
