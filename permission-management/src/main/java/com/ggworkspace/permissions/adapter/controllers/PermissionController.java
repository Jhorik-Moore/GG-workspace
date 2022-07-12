package com.ggworkspace.permissions.adapter.controllers;

import com.ggworkspace.permissions.application.service.PermissionService;
import com.ggworkspace.permissions.domain.dto.EmpoyeeRequestDto;
import com.ggworkspace.permissions.domain.entity.Employee;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getListPermissions() {
        return permissionService.getAll();
    }

    @GetMapping("/{login}")
    @ResponseStatus(HttpStatus.OK)
    public Employee getByLogin(@PathVariable String login) {
        return permissionService.getPermission(login);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @NonNull EmpoyeeRequestDto requestDto,
                       HttpServletResponse response) throws IOException {
        permissionService.create(requestDto);
        response.sendRedirect("http://localhost:2328/api/v1/permissions/list");
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody EmpoyeeRequestDto requestDto,
                       HttpServletResponse response) throws IOException {
        permissionService.update(requestDto);
        response.sendRedirect("http://localhost:2328/api/v1/permissions/list");
    }

    @DeleteMapping("/{login}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteByLogin(@PathVariable String login,
                                HttpServletResponse response) throws IOException {
        permissionService.deleteByLogin(login);
        response.sendRedirect("http://localhost:2328/api/v1/permissions/list");
    }
}
