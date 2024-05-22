package com.MongoBoundary.controllers;

import com.MongoBoundary.models.Order;
import com.MongoBoundary.models.ProductsList;
import com.MongoBoundary.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    @Value("${soap.urlPR}")
    String urlPR;

    @Value("${soap.loginRussianPost}")
    String loginPR;

    @Value("${soap.passwordRussianPost}")
    String passwordPR;

    @Value("${soap.urlRaketa}")
    String urlRaketa;

    final OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping(value = "/getDeliveryHistory/{orderId}", produces = "application/json")
    public String getStatusDeliveryByOrderId(@PathVariable String orderId) {
        return orderService.getDeliveryHistoryByOrderId(orderId, urlPR, urlRaketa, loginPR, passwordPR);
    }

    @PostMapping("/create")
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
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
    public String updateOrderById(@RequestBody Map<String, Object> data, @PathVariable String orderId){
        return orderService.updateOrderById(data, orderId);
    }

    @PostMapping("/linkProduct/{orderId}")
    public String linkProductsToOrder(@PathVariable String orderId, @RequestBody ProductsList productsList){
        return orderService.linkProductsToOrder(orderId, productsList);
    }

}
