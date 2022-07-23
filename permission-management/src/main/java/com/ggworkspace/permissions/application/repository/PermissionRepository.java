package com.ggworkspace.permissions.application.repository;

import com.ggworkspace.permissions.domain.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PermissionRepository
        extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    String GET_PERMISSION_BY_LOGIN = "SELECT * FROM public.employee WHERE employee.login = :login";
    String UPDATE_PERMISSION_BY_LOGIN = "UPDATE public.employee SET role = :role, status = :status WHERE login = :login";
    String DELETE_PERMISSION_BY_LOGIN = "DELETE  FROM public.employee WHERE employee.login = :login";
    String ADD_PERMISSION = "INSERT INTO public.employee VALUES (?, ?, ?)";


    @Query(value = GET_PERMISSION_BY_LOGIN, nativeQuery = true)
    Employee findByLogin(@Param(value = "login") String login);

    @Transactional
    @Modifying
    @Query(value = ADD_PERMISSION, nativeQuery = true)
    void addPermission(@Param(value = "login") String login,
                       @Param(value = "role") String role,
                       @Param(value = "status") String status);

    @Transactional
    @Modifying
    @Query(value = UPDATE_PERMISSION_BY_LOGIN, nativeQuery = true)
    void updateByLogin(@Param(value = "login") String login,
                       @Param(value = "role") String role,
                       @Param(value = "status") String status);

    @Transactional
    @Modifying
    @Query(value = DELETE_PERMISSION_BY_LOGIN, nativeQuery = true)
    void deleteByLogin(@Param(value = "login") String login);

}
