package com.ggworkspace.admin.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TaskStatus {

    NEW("Новая"),
    IN_WORK("В работе"),
    DONE("Выполнена"),
    CLOSED("Закрыта");

    private final String value;
}
