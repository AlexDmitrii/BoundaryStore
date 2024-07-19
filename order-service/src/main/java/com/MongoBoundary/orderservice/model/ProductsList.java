package com.MongoBoundary.orderservice.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ProductsList {

    private final List<String> productsList = new ArrayList<>();

}
