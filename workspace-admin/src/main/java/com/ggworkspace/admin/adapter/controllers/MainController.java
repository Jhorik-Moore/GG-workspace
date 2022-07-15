package com.ggworkspace.admin.adapter.controllers;

import com.ggworkspace.admin.domain.request.dto.SaveTaskRequestDto;
import com.ggworkspace.admin.application.service.SaveTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@PreAuthorize(
        "isAuthenticated() && " +
        "hasAnyAuthority('add','search','edit')"
)
@RequestMapping("/workspace.task.ua")
public class MainController {

    private final SaveTaskService saveTaskService;

    // Открываем главную страницу админки*************************
    @GetMapping
    public String openMainMenu(Model model) {
        model.addAttribute("title", "Работа над заявкой");
        return "home-admin";
    }

    //Сохраняем изменния ********************************************
    @PostMapping("/task_save")
    public String saveTask(@RequestParam String status, @RequestParam String hard,
                                 @RequestParam String category, @RequestParam String business,
                                 @RequestParam String code_close, @RequestParam String solution,
                                 @RequestParam String remark, @RequestParam String task_number,
                                 @RequestParam String fio, @RequestParam String email,
                                 @RequestParam String button, @RequestParam String product, Model model) {
        SaveTaskRequestDto dto = SaveTaskRequestDto.builder()
                .task_number(task_number)
                .code_close(code_close)
                .category(category)
                .business(business)
                .solution(solution)
                .product(product)
                .status(status)
                .remark(remark)
                .button(button)
                .email(email)
                .hard(hard)
                .fio(fio)
                .build();

        return saveTaskService.taskSave(dto, model);
    }

}

