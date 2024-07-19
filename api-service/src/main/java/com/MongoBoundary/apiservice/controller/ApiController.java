package com.MongoBoundary.apiservice.controller;

import com.MongoBoundary.apiservice.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class ApiController {

    @Value("${api.urlCurrency}")
    String urlCurrency;

    final CurrencyService currencyService;

    @GetMapping("/getCurrencyRate")
    public String getCurrencyRate() {
        return currencyService.getCurrencyRate();
    }

}
