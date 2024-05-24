package com.MongoBoundary.services.impl;

import com.MongoBoundary.services.CurrencyService;
import com.MongoBoundary.util.Status;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
@AllArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    @Override
    public String getCurrencyRate(String urlCurrency) {
        String response = send(urlCurrency);
        if (!response.equals("fail")) {
            JSONObject responseCurrency = new JSONObject(response);
            JSONObject valuteObject = responseCurrency.getJSONObject("Valute");
            JSONObject cnyObject = valuteObject.getJSONObject("CNY");
            JSONObject usdObject = valuteObject.getJSONObject("USD");
            JSONObject eurObject = valuteObject.getJSONObject("EUR");
            return "[\n" + cnyObject.getDouble("Value") * 1.085 + ",\n" +
                    usdObject.getDouble("Value") * 1.05 + ",\n" +
                    eurObject.getDouble("Value") * 1.05 + "\n]";
        }
        return "{\"status\": \"" + Status.STATUS_ERROR.getStatus() + "\"}";
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
