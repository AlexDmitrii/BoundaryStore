package com.MongoBoundary.services.impl;

import com.MongoBoundary.models.Order;
import com.MongoBoundary.repositories.OrderRepo;
import com.MongoBoundary.services.OrderService;
import com.MongoBoundary.util.Status;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;

    @Override
    public Order createOrder(Order order) {
        try {
            orderRepo.save(order);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    @Override
    public List<Order> getOrdersByUserId(String userID) {
        return getOrderByUserId(userID);
    }

    @Override
    public String deleteOrderById(String orderId) {
        Order order = getOrderById(orderId);
        if (order != null) {
            orderRepo.delete(order);
            return "Заказ №" + orderId + " был удален!";
        }

        return "Заказ № " + orderId + " не найден!";
    }

    @Override
    public String editOrderById(Order editedOrder, @PathVariable String orderId) {
        Order order = getOrderById(orderId);
        if (order != null) {
            editedOrder.setOrderId(order.getOrderId());
            orderRepo.save(editedOrder);
            return "{\"status\": \"" + Status.STATUS_SUCCESS.getStatus() + "\"}";
        }

        return "\"status\": \"" + Status.STATUS_ERROR.getStatus() + "\"";
    }

    @Override
    public Order getOrderById(String orderId) {
        return orderRepo.findAll().stream()
                .filter(el -> el.getOrderId().equals(orderId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Order> getOrderByUserId(String userID) {
        return orderRepo.findAll().stream()
                .filter(el -> el.getUserID() != null && el.getUserID().equals(userID))
                .toList();
    }
}
