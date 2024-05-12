package com.MongoBoundary.controllers;

import com.MongoBoundary.models.Order;
import com.MongoBoundary.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@CrossOrigin
public class OrderController {

    @Value("${soap.urlPR}")
    String urlPR;//80089792920747

    @Value("${soap.loginRussianPost}")
    String login;

    @Value("${soap.passwordRussianPost}")
    String password;

    @Value("${soap.urlRaketa}")
    String urlRaketa;

    final OrderService orderService;

    @GetMapping("/getStatus/{orderId}")
    public String getStatus(@PathVariable String orderId) {
        StringBuilder statusInRaketa = new StringBuilder();
//        SoapUtil.getOperationHistory(urlPR, login, password);
        try {
            Document doc = Jsoup.connect(urlRaketa + orderId)
                    .userAgent("Mozilla")
                    .timeout(5000)
                    .referrer("https://google.com")
                    .get();

            Elements steps = doc.getElementsByClass("delivery-step");
            for (Element step : steps) {
                statusInRaketa.append(step.getElementsByClass("status-text").text()).append("\n").append(step.getElementsByClass("date-text").text()).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return statusInRaketa.toString();
    }

    @PostMapping("/create")
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{userID}")
    public List<Order> getOrdersByUserId(@PathVariable String userID) {
        return orderService.getOrdersByUserId(userID);
    }

    @GetMapping("/delete/{orderId}")
    public String deleteOrderById(@PathVariable String orderId){
        return orderService.deleteOrderById(orderId);
    }

    @PostMapping("/edit/{orderId}")
    public String editOrderById(@RequestBody Order editedOrder, @PathVariable String orderId){
        return orderService.editOrderById(editedOrder, orderId);
    }

}
