package com.ggworkspace.admin.application.service;


import com.ggworkspace.permissions.domain.model.Status;
import com.ggworkspace.permissions.domain.model.entity.EmployeeRoles;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@RequiredArgsConstructor
public class UserDetail implements UserDetails {

    private final String userName;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isActive;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails getEmployeeRoles(EmployeeRoles employeeRoles) {
        return new User(
                employeeRoles.getEmployee().getLogin(),
                employeeRoles.getEmployee().getPassword(),
                employeeRoles.getStatus().equalsIgnoreCase(String.valueOf(Status.ACTIVE)),
                employeeRoles.getStatus().equalsIgnoreCase(String.valueOf(Status.ACTIVE)),
                employeeRoles.getStatus().equalsIgnoreCase(String.valueOf(Status.ACTIVE)),
                employeeRoles.getStatus().equalsIgnoreCase(String.valueOf(Status.ACTIVE)),
                employeeRoles.getRole().getAuthorities()
        );
    }
}
