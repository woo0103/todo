package com.woo.todo.controller;

import com.woo.todo.domain.Todo;
import com.woo.todo.service.TodoJpaService;
import com.woo.todo.service.TodoMemoryService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TodoController {

    private final TodoJpaService service;

    @GetMapping("/")
    public String home(Model model) {
        List<Todo> todos = service.todoList();
        model.addAttribute("todos", todos);
        return "home";
    }

    @PostMapping("/add")
    public String add(@RequestParam("title") String title, @RequestParam("description") String description) {
        Todo todo = new Todo(title, description);
        service.addTodo(todo);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        service.removeTodo(id);
        return "redirect:/";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, @RequestParam("title") String title, @RequestParam("description") String description) {
        service.updateTodo(id, title, description);
        return "redirect:/";
    }

    @PostMapping("/checked/{id}")
    public String checked(@PathVariable("id") Long id) {
        service.completedChecked(id);
        return "redirect:/";
    }
}
