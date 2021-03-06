package com.ggworkspace.admin.adapter.controllers;

import com.ggworkspace.admin.application.service.EditTaskService;
import com.ggworkspace.admin.application.service.PermissionService;
import com.ggworkspace.admin.domain.request.dto.EditTaskRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated() && hasAuthority('edit')")
@RequestMapping("/workspace.task.ua/edit_task")
public class EditTaskController {

    private final EditTaskService editTaskService;
    private final PermissionService permissionService;

    @GetMapping
    public String getEditTask(Model model,
                              Authentication authentication) {
        model.addAttribute("title", "Редактирование заявки");
        return permissionService
                .inDepthVerificationEdit(authentication);
    }

    @PostMapping
    public String taskEditSave(@RequestParam String task_numbers, @RequestParam String business,
                               @RequestParam String category, @RequestParam String code_close,
                               @RequestParam String status, @RequestParam String hard,
                               Model model) {
        EditTaskRequestDto requestDto = EditTaskRequestDto.builder()
                .task_number(task_numbers)
                .business(business)
                .category(category)
                .code_close(code_close)
                .status(status)
                .hard(hard)
                .build();

        return editTaskService.taskSave(
                requestDto,
                model);
    }

    @PostMapping("/open")
    public String openTask(@RequestParam String task_number,
                           Model model) {
        return editTaskService.openEditTask(
                task_number,
                model);
    }
}
