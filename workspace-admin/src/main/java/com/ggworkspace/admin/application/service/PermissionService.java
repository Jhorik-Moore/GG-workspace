package com.ggworkspace.admin.application.service;

import java.security.Principal;

public interface PermissionService {

    String HOME_ADMIN = "home-admin";
    String HOME_OPER = "home-oper";
    String EDIT_TASK = "edit-task";

    String inDepthVerification(String option, Principal principal);
}
