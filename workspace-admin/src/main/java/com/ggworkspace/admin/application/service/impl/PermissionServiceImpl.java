package com.ggworkspace.admin.application.service.impl;

import com.ggworkspace.admin.application.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Slf4j
@Service
public class PermissionServiceImpl
        implements PermissionService {

    private static final String PERMISSION_EDIT = "edit";

    @Override
    public String inDepthVerificationMain(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getAuthorities()
                .stream()
                .anyMatch(role -> role.getAuthority().equalsIgnoreCase(PERMISSION_EDIT)) ?
                HOME_ADMIN : HOME_OPER;
    }

    @Override
    public String inDepthVerificationEdit(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getAuthorities()
                .stream()
                .anyMatch(role -> role.getAuthority().equalsIgnoreCase(PERMISSION_EDIT)) ?
                EDIT_TASK : " У вас отсутствуют права к данному ресурсу";
    }
}
