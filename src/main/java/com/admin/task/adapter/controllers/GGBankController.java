package com.admin.task.adapter.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GGBankController {

    //Открываем главную страницу условного банка****************************
    @GetMapping("/gg-bank.ua")
    public String openClientBank(Model model) {
        return "site-form";
    }
}
