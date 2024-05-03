package com.MongoBoundary.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class SoapUtil {

    @Value("${soap.urlPR}")
    static String urlPR;//80089792920747

    @Value("${soap.loginRussianPost}")
    static String login;

    @Value("${soap.passwordRussianPost}")
    static String password;

    public static void decodeSoap(String body) {}

    public static String getOperationHistory(){
        String soapRequest = "  <soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:oper=\"http://russianpost.org/operationhistory\" xmlns:data=\"http://russianpost.org/operationhistory/data\" xmlns:ns1=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "   <soap:Header/>\n" +
                "   <soap:Body>\n" +
                "      <oper:getOperationHistory>\n" +
                "         <!--Optional:-->\n" +
                "         <data:OperationHistoryRequest>\n" +
                "            <data:Barcode>ED368922712RU</data:Barcode>\n" +
                "            <data:MessageType>0</data:MessageType>\n" +
                "            <!--Optional:-->\n" +
                "            <data:Language>RUS</data:Language>\n" +
                "         </data:OperationHistoryRequest>\n" +
                "         <!--Optional:-->\n" +
                "         <data:AuthorizationHeader ns1:mustUnderstand=\"?\">\n" +
                "            <data:login>" + login + "</data:login>\n" +
                "            <data:password>" + password + "</data:password>\n" +
                "         </data:AuthorizationHeader>\n" +
                "      </oper:getOperationHistory>\n" +
                "   </soap:Body>\n" +
                "</soap:Envelope>"; // Ваш SOAP-запрос

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("application/soap+xml"));

        HttpEntity<String> request = new HttpEntity<>(soapRequest, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(urlPR, HttpMethod.POST, request, String.class);
        String responseBody = response.getBody();

        return responseBody; // Обработка ответа от SOAP-сервиса

    }

}
