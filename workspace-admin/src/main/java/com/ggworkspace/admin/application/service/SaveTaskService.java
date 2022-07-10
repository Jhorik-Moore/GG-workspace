package com.ggworkspace.admin.application.service;

import com.ggworkspace.admin.domain.request.dto.SaveTaskRequestDto;
import org.springframework.ui.Model;

public interface SaveTaskService {

    String REMARK_CONTACT_CLIENT = """
    Коммуникация не проведена со статусом заявки 'Выполнена'. Предоставьте ответ инициатору""";
    String EMAIL_MESSAGE_DONE = "Ответ успешно отправлен на Email";
    String SAVE_CHANGES_TITLE = "Сохранение изменений";
    String SAVE_CHANGES_MESSAGE = "Изменения сохранены успешно!";

    String taskSave(SaveTaskRequestDto dto,
                    Model model);
}
