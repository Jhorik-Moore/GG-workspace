package com.ggworkspace.admin.adapter.controllers;

import com.ggworkspace.admin.application.service.CreateTaskService;
import com.ggworkspace.admin.domain.request.dto.CreateTaskRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/workspace.task.ua/send_task")
@RequiredArgsConstructor
public class CreateTaskController {

    private final CreateTaskService createTaskService;

    // Открываем страницу создания заявки ************************
    @GetMapping
    public String openCreateTask(Model model) {
        model.addAttribute("title", "Создание заявки");
        return "task-add";
    }

    //Получаем переменные полей/ создаем заявку **********************************************
    @PostMapping
    public String createTask(@RequestParam String product, @RequestParam String detail_problem,
                            @RequestParam String surname, @RequestParam String name,
                            @RequestParam String father_name, @RequestParam String mob,
                            @RequestParam String email, @RequestParam String check,
                            Model model) throws IllegalStateException {
        CreateTaskRequestDto dto = CreateTaskRequestDto.builder()
                .detail_problem(detail_problem)
                .father_name(father_name)
                .surname(surname)
                .product(product)
                .check(check)
                .email(email)
                .name(name)
                .mob(mob)
                .build();

        return createTaskService.createTask(
                dto,
                model);
    }

    // Открываем поданную заявку *****************************************************************
    @GetMapping("/open_task")
    public String openTask(@RequestParam String Id,
                            Model model) {
        return createTaskService.openCreatedTask(
                Id,
                model);
    }


}
