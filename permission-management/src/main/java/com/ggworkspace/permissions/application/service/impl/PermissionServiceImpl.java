package com.ggworkspace.permissions.application.service.impl;

import com.ggworkspace.permissions.application.repository.PermissionRepository;
import com.ggworkspace.permissions.application.service.PermissionService;
import com.ggworkspace.permissions.domain.dto.EmpoyeeRequestDto;
import com.ggworkspace.permissions.domain.model.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    @Override
    public Optional<Employee> getPermission(final String login) {
        return Optional.ofNullable(permissionRepository.findByLogin(login));
    }

    @Override
    public List<Employee> getAll() {
        return permissionRepository.findAll();
    }

    @Override
    public void create(final EmpoyeeRequestDto dto) {
        if (isValid(dto)) {
            setUpperCase(dto);
            if (!isLoginAlreadyExist(dto.getLogin())) {
                permissionRepository.addPermission(
                        dto.getLogin(),
                        dto.getRole(),
                        dto.getStatus());
            } else {
                throw new IllegalStateException("Error with add permission : login %s already exist!"
                        .formatted(dto.getLogin()));
            }
        } else {
            throw new IllegalStateException("Error with add permission : invalid request!\n" + dto);
        }
    }

    @Override
    public void update(final EmpoyeeRequestDto dto) {
        setUpperCase(dto);
        if(isLoginAlreadyExist(dto.getLogin())) {
            permissionRepository.updateByLogin(
                    dto.getLogin(),
                    dto.getRole(),
                    dto.getStatus()
            );
        } else {
            throw new IllegalStateException("Error with update permission : login %s not exist!"
                    .formatted(dto.getLogin()));
        }
    }

    private boolean isValid(EmpoyeeRequestDto dto) {
        return !dto.getLogin().isEmpty() &&
                !dto.getLogin().isBlank() &&
                !dto.getRole().isEmpty() &&
                !dto.getStatus().isEmpty();
    }

    private void setUpperCase(EmpoyeeRequestDto dto){
        dto.setLogin(dto.getLogin().trim().toUpperCase());
        dto.setRole(dto.getRole().trim().toUpperCase());
        dto.setStatus(dto.getStatus().trim().toUpperCase());
    }

    private boolean isLoginAlreadyExist(final String login) {
        return permissionRepository.findAll().stream()
                .anyMatch(employee -> employee.getLogin().equals(login));
    }

    @Override
    public void deleteByLogin(final String login) {
        if(isLoginAlreadyExist(login.toUpperCase())) {
            permissionRepository.deleteByLogin(login);
        } else {
            throw new IllegalStateException("Error deleting permission : login %s not exist!"
                    .formatted(login));
        }
    }
}
