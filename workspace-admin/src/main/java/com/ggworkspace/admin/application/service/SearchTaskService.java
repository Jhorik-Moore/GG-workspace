package com.ggworkspace.admin.application.service;

import org.springframework.ui.Model;

public interface SearchTaskService {

    String EMPTY_TASK_LIST = "Нет записей по указанным параметрам...";

    String searchResult(String filter,
                        String search_param,
                        Model model);

    String findTasks(Model model);

    String openTaskFromSearch(String Id,
                              Model model);
}
