package com.admin.task.domain.request.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
public class EditTaskRequestDto
        implements Serializable {

    @Serial
    private static final long serialVersionUID = -6832034792730675575L;

    String task_number;
    String status;
    String hard;
    String category;
    String business;
    String code_close;
}
