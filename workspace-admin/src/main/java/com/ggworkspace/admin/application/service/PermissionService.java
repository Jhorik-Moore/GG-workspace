package com.ggworkspace.admin.application.service;

import org.springframework.security.core.Authentication;

public interface PermissionService {

    String HOME_ADMIN = "home-admin";
    String HOME_OPER = "home-oper";
    String EDIT_TASK = "edit-task";

    String inDepthVerificationMain(Authentication authentication);

    String inDepthVerificationEdit(Authentication authentication);
}
