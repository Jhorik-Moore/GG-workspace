package com.ggworkspace.admin.adapter.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/permission-editor")
@PreAuthorize("isAuthenticated() && hasAuthority('edit')")
public class PermissionEditorController {

    @GetMapping
    public String getPermissionEditorPage(Model model){
        model.addAttribute("title", "Редактирование ролей");
        return "edit-roles";
    }


}
