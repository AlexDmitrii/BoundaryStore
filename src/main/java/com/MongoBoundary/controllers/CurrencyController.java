package com.MongoBoundary.controllers;

import com.MongoBoundary.models.Order;
import com.MongoBoundary.services.CurrencyService;
import com.MongoBoundary.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CurrencyController {
    @Value("${api.urlCurrency}")
    String urlCurrency;

    final CurrencyService currencyService;

    @GetMapping("/getCurrencyRate")
    public String getCurrencyRate() {
        System.out.println("getCurrencyRate == " + urlCurrency);
        return currencyService.getCurrencyRate(urlCurrency);
    }

}
