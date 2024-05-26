package com.MongoBoundary.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SizeEnum {

    XS("XS"),
    S("S"),
    M("M"),
    L ("L"),
    XL("XL"),
    XXL("XXL"),
    XXXL("XXXL");

    private final String size;
}
