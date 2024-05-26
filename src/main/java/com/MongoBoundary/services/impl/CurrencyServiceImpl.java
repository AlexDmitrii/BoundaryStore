package com.MongoBoundary.services.impl;

import com.MongoBoundary.services.CurrencyService;
import com.MongoBoundary.util.Status;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    @Value("${api.urlCurrency}")
    String urlCurrency;

    @Override
    public String getCurrencyRate() {
        String response = send(urlCurrency);
        if (!response.equals("fail")) {
            JSONObject responseCurrency = new JSONObject(response);
            JSONObject valuteObject = responseCurrency.getJSONObject("Valute");
            JSONObject cnyObject = valuteObject.getJSONObject("CNY");
            JSONObject usdObject = valuteObject.getJSONObject("USD");
            JSONObject eurObject = valuteObject.getJSONObject("EUR");
            return "[" + cnyObject.getDouble("Value") * 1.085 + "," +
                    usdObject.getDouble("Value") * 1.05 + "," +
                    eurObject.getDouble("Value") * 1.05 + "]";
        }
        return Status.returnStatus(Status.STATUS_ERROR);
    }

    public String send(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("application/json"));

        HttpEntity<String> request = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
            return response.getBody();
        } catch (Exception e) {
            return "fail";
        }
    }
}
