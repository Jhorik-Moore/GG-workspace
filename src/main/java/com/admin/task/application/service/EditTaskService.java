package com.admin.task.application.service;

import com.admin.task.domain.request.dto.EditTaskRequestDto;
import org.springframework.ui.Model;

public interface EditTaskService {

    String taskSave(EditTaskRequestDto requestDto,
                    Model model);

    String openEditTask(String taskId,
                        Model model);
}
