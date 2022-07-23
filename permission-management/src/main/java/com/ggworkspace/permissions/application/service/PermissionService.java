package com.ggworkspace.permissions.application.service;

import com.ggworkspace.permissions.domain.dto.EmpoyeeRequestDto;
import com.ggworkspace.permissions.domain.model.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface PermissionService {

    Optional<Employee> getPermission(String login);

    List<Employee> getAll();

    void create(EmpoyeeRequestDto dto);

    void update(EmpoyeeRequestDto dto);

    void deleteByLogin(String login);

}
