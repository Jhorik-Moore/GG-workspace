package com.ggworkspace.permissions.domain.model;

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
