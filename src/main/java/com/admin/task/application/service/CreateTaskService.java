package com.admin.task.application.service;

import com.admin.task.domain.request.dto.CreateTaskRequestDto;
import org.springframework.ui.Model;

public interface CreateTaskService {

    String createTask(CreateTaskRequestDto dto,
                      Model model) throws IllegalStateException;

    String openCreatedTask(String Id,
                           Model model);
}
