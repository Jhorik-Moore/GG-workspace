package com.ggworkspace.admin.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
public enum Permission {

    CREATE("add"),
    SEARCH("search"),
    EDIT("edit");

    private final String permissions;
}
