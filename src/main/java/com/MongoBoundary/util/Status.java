package com.MongoBoundary.util;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {

    STATUS_OK("ok"),
    STATUS_NOT_FOUND("not found");

    private final String status;
}
