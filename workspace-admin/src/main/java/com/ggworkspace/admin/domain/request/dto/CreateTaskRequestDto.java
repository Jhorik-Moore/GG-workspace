package com.ggworkspace.admin.domain.request.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Builder
@Getter
@Setter
public class CreateTaskRequestDto
        implements Serializable {

    @Serial
    private static final long serialVersionUID = 1319683043410862564L;

    String product;
    String detail_problem;
    String surname;
    String name;
    String father_name;
    String mob;
    String email;
    String check;

}
