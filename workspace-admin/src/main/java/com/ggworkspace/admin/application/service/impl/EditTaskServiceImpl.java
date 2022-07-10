package com.ggworkspace.admin.application.service.impl;

import com.ggworkspace.admin.domain.model.entity.Task;
import com.ggworkspace.admin.application.repository.TaskModelRepository;
import com.ggworkspace.admin.application.service.EditTaskService;
import com.ggworkspace.admin.domain.request.dto.EditTaskRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
public class EditTaskServiceImpl
        implements EditTaskService {

    private final TaskModelRepository taskModelRepository;

    @Override
    public String taskSave(EditTaskRequestDto requestDto,
                           Model model) {
        String[] taskNumberList = null;
        if (requestDto.getTask_number().length() > 10) {
            taskNumberList = requestDto.getTask_number().split(",");
            for (String task_number : taskNumberList) {
                taskModelRepository.UpdateCloseTask(task_number.trim(),
                        requestDto.getStatus(),
                        requestDto.getHard(),
                        requestDto.getCategory(),
                        requestDto.getBusiness(),
                        requestDto.getCode_close());
            }
        } else {
            taskModelRepository.UpdateCloseTask(
                    requestDto.getTask_number(),
                    requestDto.getStatus(),
                    requestDto.getHard(),
                    requestDto.getCategory(),
                    requestDto.getBusiness(),
                    requestDto.getCode_close());
        }
        if(taskNumberList != null){
            requestDto.setTask_number(taskNumberList[taskNumberList.length - 1].trim());
        }
        setAttributeToModel(requestDto, model);

        return "edit-task-result";
    }

    @Override
    public String openEditTask(String taskId, Model model) {
            Iterable<Task> taskList = taskModelRepository.findByTask_number(taskId.trim());
            model.addAttribute("taskModel", taskList);
            model.addAttribute("title", "Просмотр заявки");

        return "open-task";
    }

    private void setAttributeToModel(EditTaskRequestDto requestDto,
                                     Model model) {
        model.addAttribute("task_number", requestDto.getTask_number());
        model.addAttribute("task_bus", requestDto.getBusiness());
        model.addAttribute("task_cat", requestDto.getCategory());
        model.addAttribute("task_stat", requestDto.getStatus());
        model.addAttribute("task_codecl", requestDto.getCode_close());
        model.addAttribute("task_hard", requestDto.getHard());
        model.addAttribute("title", "Результат редактирования");
    }
}
