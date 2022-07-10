package com.ggworkspace.admin.application.service;

import com.ggworkspace.admin.domain.request.dto.EditTaskRequestDto;
import org.springframework.ui.Model;

public interface EditTaskService {

    String taskSave(EditTaskRequestDto requestDto,
                    Model model);

    String openEditTask(String taskId,
                        Model model);
}
