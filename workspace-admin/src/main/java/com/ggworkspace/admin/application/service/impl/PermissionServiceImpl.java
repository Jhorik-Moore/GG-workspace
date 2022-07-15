package com.ggworkspace.admin.application.service.impl;

import com.ggworkspace.admin.application.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Slf4j
@Service
public class PermissionServiceImpl
        implements PermissionService {

    @Override
    public String inDepthVerification(String option, Principal principal) {
        log.info("principal -> user {} come in...", principal.getName());
        if (option.equalsIgnoreCase("main")) {
            return principal.getName().equals("admin") ? HOME_ADMIN : HOME_OPER;
        } else if (option.equalsIgnoreCase("edit")) {
            return principal.getName().equals("admin") ? EDIT_TASK : " У вас отсутствуют права к данному ресурсу";
        }
        throw new IllegalStateException("Неопознаный тип запроса на ресурс");
    }
}
