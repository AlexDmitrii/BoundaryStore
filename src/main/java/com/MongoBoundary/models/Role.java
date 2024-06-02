package com.MongoBoundary.models;

import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;
import java.util.stream.Collectors;

import static com.MongoBoundary.models.Permissions.*;

@Getter
@RequiredArgsConstructor
public enum Role {

    USER(Collections.emptySet()),

    ADMIN(Set.of(
            ADMIN_ALL,
            DEV_ALL,
            MANAGER_ALL
    )),

    DEV(Set.of(
            DEV_ALL,
            MANAGER_ALL
    )),

    MANAGER(Set.of(
            MANAGER_ALL
    ));

    private final Set<Permissions> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;

    }
}
