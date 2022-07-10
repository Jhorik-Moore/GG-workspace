package com.ggworkspace.permissions.adapter.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/permissions")
public class PermissionController {

    @GetMapping("/{login}")
    public ResponseEntity<?> getByLogin(@PathVariable String login) {
    return null;
    }
}
