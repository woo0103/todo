package com.woo.todo.controller;

import com.woo.todo.domain.Todo;
import com.woo.todo.service.TodoJpaService;
import com.woo.todo.service.TodoMemoryService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/todo")
@Slf4j
public class TodoController {

    private final TodoJpaService service;

    // 홈 화면
    // 전체 todoList 출력
    @GetMapping()
    public String home(Model model) {
        List<Todo> todos = service.todoList();
        model.addAttribute("todos", todos);
        log.info("todohome");
        return "todohome";
    }

    // todo 추가
    @PostMapping("/add")
    public String add(@RequestParam("title") String title, @RequestParam("description") String description) {
        Todo todo = new Todo(title, description);
        service.addTodo(todo);
        log.info("addTodo={}", todo);
        return "redirect:/todo";
    }

    // todo 1개 삭제
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        service.removeTodo(id);
        log.info("removeTodo");
        return "redirect:/todo";
    }

    // todo 내용 수정
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, @RequestParam("title") String title, @RequestParam("description") String description) {
        service.updateTodo(id, title, description);
        log.info("updateTodo");
        return "redirect:/todo";
    }

    // completed 값 변경
    // O,X 전환
    // 로그 안찍음
    @PostMapping("/checked/{id}")
    public String checked(@PathVariable("id") Long id) {
        service.completedChecked(id);;
        return "redirect:/todo";
    }

    @PostMapping("/clear")
    public String clear() {
        service.removeAll();
        log.info("todoClear");
        return "redirect:/todo";
    }
}
