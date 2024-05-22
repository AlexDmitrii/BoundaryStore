package com.MongoBoundary.services;

import com.MongoBoundary.models.Order;
import com.MongoBoundary.models.Product;
import com.MongoBoundary.models.ProductsList;

import java.util.List;
import java.util.Map;

public interface OrderService {

    Order createOrder(Order order);
    List<Order> getAllOrders();
    List<Order> getOrdersByUserId(String userID);
    String deleteOrderById(String orderId);
    String updateOrderById(Map<String, Object> data, String orderId);
    Order getOrderById(String orderId);
    List<Order> getOrderByUserId(String userID);
    String getDeliveryHistoryByOrderId(String orderId, String urlPR, String urlRaketa, String loginPR, String passwordPR);

    String linkProductsToOrder(String orderId, ProductsList orderList);

    List<Product> getProductsByOrderId(String orderId);

}
