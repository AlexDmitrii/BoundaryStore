package com.MongoBoundary.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permissions {

    ADMIN_ALL("admin:all"),
    DEV_ALL("dev:all"),
    MANAGER_ALL("manager:all");

    private final String permission;

}
