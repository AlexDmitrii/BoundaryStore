package com.MongoBoundary.MongoBoundary.Controller;

import com.MongoBoundary.MongoBoundary.Model.Order;
import com.MongoBoundary.MongoBoundary.Repository.OrderRepo;
import jakarta.xml.soap.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;

@RestController
public class MainController {

    @Autowired
    OrderRepo orderRepo;

    final String urlRaketa = "https://raketacn.ru/?option=com_lk&task=teletestyTrack&track=";
    final String urlPR = "https://tracking.russianpost.ru/rtm34";//80089792920747

    final String apiKeyPs = "a29zdHlhLmZhZDFAZ21haWwuY29tOjI1MTE5ODBtYVg=";

    @GetMapping("/getStatus/{orderId}")
    public void getStatus(@PathVariable String orderId){
        StringBuilder statusInRaketa = new StringBuilder();
        getOperationHistory();
        try {
            Document doc = Jsoup.connect(urlRaketa + orderId)
                    .userAgent("Mozilla")
                    .timeout(5000)
                    .referrer("https://google.com")
                    .get();
            System.out.println("80089792920747");
            Elements steps = doc.getElementsByClass("delivery-step");
            for (Element step: steps) {
                statusInRaketa.append(step.getElementsByClass("status-text").text()).append("\n").append(step.getElementsByClass("date-text").text()).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        Order order = new Order(Integer.valueOf(orderId), statusInRaketa.toString());вв
//        orderRepo.save(order);
    }

    public void decodeSoap(String body) {


    }


    public void getOperationHistory(){
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
                "            <data:login></data:login>\n" +
                "            <data:password></data:password>\n" +
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
        System.out.println(responseBody); // Обработка ответа от SOAP-сервиса



    }




}
