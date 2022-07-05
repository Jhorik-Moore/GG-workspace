package com.admin.task.adapter.controllers;

import com.admin.task.application.service.SearchTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/workspace.task.ua/search_task")
public class SearchTaskController {

    private final SearchTaskService searchTaskService;

    //Поиск заявки ***************************************************
    @GetMapping
    public String openSearchMenu(Model model) {
        return "search-task";
    }

    @PostMapping
    public String searchByParam(Model model) {
        return "redirect:/workspace.task.ua/search_task/result";
    }


    // Результат Поиска заявки ***************************************
    @PostMapping("/result")
    public String searchResult(@RequestParam String filter,
                               @RequestParam String search_param,
                               Model model) {

        return searchTaskService.searchResult(
                filter,
                search_param,
                model);
    }

    // Поиск незакрытых заявок ***************************************
    @GetMapping("/find")
    public String findTasks(Model model) {
        return searchTaskService.findTasks(model);
    }

    //Открываем выбранную из поиска заявку ***************************
    @GetMapping("/result/open_task")
    public String openTaskFromSearch(@RequestParam String Id,
                                     Model model) {
       return searchTaskService.openTaskFromSearch(
               Id,
               model);
    }
}
