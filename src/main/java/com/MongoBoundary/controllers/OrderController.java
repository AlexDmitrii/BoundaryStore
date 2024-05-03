package com.MongoBoundary.controllers;

import com.MongoBoundary.models.Order;
import com.MongoBoundary.repositories.OrderRepo;
import com.MongoBoundary.util.Status;
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
public class OrderController {

    final OrderRepo orderRepo;

    @Value("${soap.urlRaketa}")
    String urlRaketa;

    @GetMapping("/getStatus/{orderId}")
    public String getStatus(@PathVariable String orderId) {
        StringBuilder statusInRaketa = new StringBuilder();
//        SoapUtil.getOperationHistory();
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
        try {
            orderRepo.save(order);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return order;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    @GetMapping("/{userID}")
    public List<Order> getOrdersByUserId(@PathVariable String userID) {
        return getOrderByUserId(userID);
    }

    @GetMapping("/delete/{orderId}")
    public String deleteOrderById(@PathVariable String orderId){
        Order order = getOrderById(orderId);
        if (order != null) {
            orderRepo.delete(order);
            return "Заказ №" + order + " был удален!";
        }

        return "Заказ № " + orderId + " не найден!";
    }

    @PostMapping("/edit/{orderId}")
    public String editOrderById(@RequestBody Order editedOrder, @PathVariable String orderId){
        Order order = getOrderById(orderId);
        if (order != null) {
            //TODO: дописать
            return "{\"status\": \"" + Status.STATUS_OK.getStatus() + "\"}";
        }

        return "\"status\": \"" + Status.STATUS_NOT_FOUND.getStatus() + "\"";
    }

    public Order getOrderById(String orderId){
        return orderRepo.findAll().stream()
                .filter(el -> el.getOrderId().equals(orderId))
                .findFirst()
                .orElse(null);
    }

    public List<Order> getOrderByUserId(String userID){
        return orderRepo.findAll().stream()
                .filter(el -> el.getUserID().equals(userID))
                .toList();
    }

}
