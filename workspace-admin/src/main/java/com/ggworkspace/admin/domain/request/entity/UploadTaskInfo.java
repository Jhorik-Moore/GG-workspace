package com.ggworkspace.admin.domain.request.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class UploadTaskInfo
        implements Serializable {

    @Serial
    private static final long serialVersionUID = -4476389647918814022L;

    String task_number;
    String product;
    String name;
    String surname;
    String father_name;
    String mob;
    String email;
    String check;
    String detail_problem;
    String date_time;
    String status;
}
