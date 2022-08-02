package com.ggworkspace.permissions.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class EmpoyeeRequestDto
        implements Serializable {

    @Serial
    private static final long serialVersionUID = 6538167988590557240L;

    private String login,
            role,
            status;
}
