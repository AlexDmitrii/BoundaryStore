package com.MongoBoundary.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum RoleEnum {

    ROLE_USER(1, "USER"),
    ROLE_MANAGER(2, "MANAGER"),
    ROLE_ADMIN(3, "ADMIN"),
    ROLE_DEV(4, "DEV");

    private final Integer roleLevel;
    private final String roleName;

    public static RoleEnum getRoleEnum(Integer roleLevel) {
        return Arrays.stream(RoleEnum.values())
                .filter(role -> role.roleLevel.equals(roleLevel))
                .findAny()
                .orElse(null);
    }

}
