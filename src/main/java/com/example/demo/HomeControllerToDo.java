package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller

public class HomeControllerToDo {
    @Autowired
    ToDoRepository toDoRepository;

    @RequestMapping("/")
    public String listTask(Model model){
        model.addAttribute("tasks", toDoRepository.findAll());
        return "tasklist";

    }
    @GetMapping("/addtask")
    public String jobForm(Model model){
        model.addAttribute("task", new ToDo());
        return "taskform";
    }
    @PostMapping("/processing")
    public String processForm(@Valid ToDo toDo, BindingResult result){
        if (result.hasErrors()){
            return "taskform";
        }
        toDoRepository.save(toDo);
        return "redirect:/";
    }
}
