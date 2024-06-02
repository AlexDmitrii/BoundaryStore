package com.MongoBoundary.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {

    STATUS_SUCCESS("success"),
    STATUS_INFO("info"),
    STATUS_WARNING("warning"),
    STATUS_ERROR("error"),
    STATUS_NOT_FOUND("not_found");

    private final String status;

    public static String returnStatus(Status status){
        return "{\"status\": \"" + status.getStatus() + "\"}";
    };
}
