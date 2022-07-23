package com.ggworkspace.permissions.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum Role {

    OPER(Set.of(
            Permission.CREATE,
            Permission.SEARCH)),
    ADMIN(Set.of(
            Permission.CREATE,
            Permission.SEARCH,
            Permission.EDIT));

    private final Set<Permission> permissions;

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermissions()))
                .collect(Collectors.toSet());
    }
}
