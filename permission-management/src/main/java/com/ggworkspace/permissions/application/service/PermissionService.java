package com.ggworkspace.permissions.application.service;

import com.ggworkspace.permissions.domain.dto.EmpoyeeRequestDto;
import com.ggworkspace.permissions.domain.model.entity.EmployeeRoles;

import java.util.List;
import java.util.Optional;

public interface PermissionService {

    Optional<EmployeeRoles> getPermission(String login);

    List<EmployeeRoles> getAll();

    void create(EmpoyeeRequestDto dto);

    void update(EmpoyeeRequestDto dto);

    void deleteByLogin(String login);

}
