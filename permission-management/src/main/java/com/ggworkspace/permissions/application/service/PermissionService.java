package com.ggworkspace.permissions.application.service;

import com.ggworkspace.permissions.domain.dto.EmpoyeeRequestDto;
import com.ggworkspace.permissions.domain.entity.Employee;

import java.util.List;

public interface PermissionService {

    Employee getPermission(String login);

    List<Employee> getAll();

    void create(EmpoyeeRequestDto dto);

    void update(EmpoyeeRequestDto dto);

    void deleteByLogin(String login);

}
