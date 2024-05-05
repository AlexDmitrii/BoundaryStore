package com.MongoBoundary.util;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {

    STATUS_SUCCESS("success"),
    STATUS_INFO("info"),
    STATUS_WARNING("warning"),
    STATUS_ERROR("error");

    private final String status;
}
