package com.ggworkspace.permissions.domain.model.entity;

import com.ggworkspace.permissions.domain.model.Role;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee_roles")
public class EmployeeRoles
        implements Serializable {

    @Serial
    private static final long serialVersionUID = -1435332373958395667L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "login")
    private String login;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "password", referencedColumnName = "password")
    private Employee employee;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;
    @Column(name = "status")
    private String status;

}
