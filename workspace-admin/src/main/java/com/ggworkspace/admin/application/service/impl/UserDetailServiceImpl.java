package com.ggworkspace.admin.application.service.impl;

import com.ggworkspace.admin.application.service.UserDetail;
import com.ggworkspace.permissions.domain.model.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Service("userDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {

    @Value("${restful.web.permission-management.host}")
    private String permissionsHost;
    @Value("${restful.web.permission-management.uri}")
    private String permissionsUri;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return UserDetail.getEmployee(getUserPermissionsByLogin(login));
    }

    private Employee getUserPermissionsByLogin(final String login) {
        return WebClient.create()
                .get()
                .uri(permissionsHost + permissionsUri + login)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(Employee.class)
                .block();
    }


}
