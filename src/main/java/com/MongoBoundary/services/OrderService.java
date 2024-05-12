package com.MongoBoundary.services;

import com.MongoBoundary.models.Order;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface OrderService {

    Order createOrder(Order order);
    List<Order> getAllOrders();
    List<Order> getOrdersByUserId(String userID);
    String deleteOrderById(String orderId);
    String editOrderById(Order editedOrder, @PathVariable String orderId);
    Order getOrderById(String orderId);
    List<Order> getOrderByUserId(String userID);
    String getDeliveryHistoryByOrderId(String orderId, String urlPR, String urlRaketa, String loginPR, String passwordPR);

}
