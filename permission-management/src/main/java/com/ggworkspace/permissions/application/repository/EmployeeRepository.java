package com.ggworkspace.permissions.application.repository;

import com.ggworkspace.permissions.domain.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {
}
