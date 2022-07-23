package com.ggworkspace.permissions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages={"com.ggworkspace.permissions"})
public class PermissionManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(PermissionManagementApplication.class, args);
    }

}
