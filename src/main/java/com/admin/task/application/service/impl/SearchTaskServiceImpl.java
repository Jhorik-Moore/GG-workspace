package com.admin.task.application.service.impl;

import com.admin.task.application.repository.TaskModelRepository;
import com.admin.task.application.service.SearchTaskService;
import com.admin.task.domain.model.TaskStatus;
import com.admin.task.domain.model.entity.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchTaskServiceImpl
        implements SearchTaskService {

    private static final String TASK_ID = "task_number";
    private static final String PHONE = "phone";
    private static final String EMAIL = "email";
    private final TaskModelRepository taskModelRepository;


    @Override
    public String searchResult(String filter,
                               String search_param,
                               Model model) {
        List<Task> taskList;
        switch (filter) {
            case TASK_ID -> taskList = taskModelRepository.findByTask_number(search_param);
            case PHONE -> taskList = taskModelRepository.findByPhone(search_param);
            case EMAIL -> taskList = taskModelRepository.findByEmail(search_param);
            default -> throw new IllegalStateException("Unexpected value: " + filter);
        }
        if (!taskList.isEmpty()) {
            model.addAttribute("rows", taskList.size());
            model.addAttribute("taskModel", taskList);
        } else {
            model.addAttribute("Result", EMPTY_TASK_LIST);
        }

        return "search-task-result";
    }

    @Override
    public String findTasks(Model model) {
        List<Task> taskList = taskModelRepository.findTasks();
        model.addAttribute("rows", taskList.size());
        model.addAttribute("taskModel", taskList);

        return "search-task-result";
    }

    @Override
    public String openTaskFromSearch(String Id, Model model) {
        List<Task> taskList = taskModelRepository.findByTask_number(Id);

        for (Task task : taskList) {
            if (task.getStatus().equals(TaskStatus.CLOSED.getValue())) {
                addParametersToModel(Id, model, taskList);
                return "open-closed-task";
            }
        }
        addParametersToModel(Id, model, taskList);

        return "open-task";
    }

    private void addParametersToModel(String Id, Model model, List<Task> taskList) {
        model.addAttribute("taskModel", taskList);
        model.addAttribute("title", Id);
    }

}
