package com.ggworkspace.admin.application.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ggworkspace.admin.application.service.UserDetail;
import com.ggworkspace.permissions.domain.model.entity.EmployeeRoles;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@RequiredArgsConstructor
@Service("userDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {

    @Value("${restful.web.permission-management.host}")
    private String permissionsHost;
    @Value("${restful.web.permission-management.uri}")
    private String permissionsUri;
    private final ObjectMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        try {
            log.info(mapper.writer()
                    .withDefaultPrettyPrinter()
                    .writeValueAsString(getUserPermissionsByLogin(login)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return UserDetail.getEmployeeRoles(getUserPermissionsByLogin(login));
    }

    private EmployeeRoles getUserPermissionsByLogin(final String login) {
        return WebClient.create()
                .get()
                .uri(permissionsHost + permissionsUri + login)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(EmployeeRoles.class)
                .block();
    }


}
