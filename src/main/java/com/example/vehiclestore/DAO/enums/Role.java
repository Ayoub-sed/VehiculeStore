/* package com.example.vehiclestore.DAO.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.example.vehiclestore.DAO.enums.Privilege.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public enum Role {
    ADMIN(
        Set.of(READ_PRIVILEGE, WRITE_PRIVILEGE, UPDATE_PRIVILEGE, DELETE_PRIVILEGE)
),
USER(
        Set.of(READ_PRIVILEGE)
);

@Getter
private final Set<Privilege> privileges;

public List<SimpleGrantedAuthority> getAuthorities() {
    List<SimpleGrantedAuthority> authorities = getPrivileges()
            .stream()
            .map(privilege -> new SimpleGrantedAuthority(privilege.name()))
            .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
}
} */