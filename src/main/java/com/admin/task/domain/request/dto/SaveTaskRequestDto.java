package com.admin.task.domain.request.dto;

import lombok.Builder;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Builder
public class SaveTaskRequestDto
        implements Serializable {

    @Serial
    private static final long serialVersionUID = -469469266402634805L;

    String task_number,
            status,
            hard,
            category,
            business,
            code_close,
            solution,
            remark,
            fio,
            email,
            button,
            product;

}
