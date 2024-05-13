package com.MongoBoundary.services.impl;

import com.MongoBoundary.models.Order;
import com.MongoBoundary.repositories.OrderRepo;
import com.MongoBoundary.services.OrderService;
import com.MongoBoundary.util.Constant;
import com.MongoBoundary.util.SoapUtil;
import com.MongoBoundary.util.Status;
import com.MongoBoundary.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;

    private final MongoTemplate mongoTemplate;

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
            try{
                orderRepo.delete(order);
                return Status.returnStatus(Status.STATUS_SUCCESS);
            } catch (Exception e){
                return Status.returnStatus(Status.STATUS_ERROR);
            }
        }

        return Status.returnStatus(Status.STATUS_NOT_FOUND);
    }

    @Override
    public String editOrderById(Map<String, Object> data, String orderId) {
        if (data != null && !data.isEmpty()) {
            Query query = Util.getQueryById(orderId);
            Update update = Util.fillDataForUpdate(data);

            mongoTemplate.updateFirst(query, update, Order.class, Constant.ORDER_DB_NAME);

            return Status.returnStatus(Status.STATUS_SUCCESS);
        }

        return Status.returnStatus(Status.STATUS_ERROR);
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

    @Override
    public String getDeliveryHistoryByOrderId(String orderId, String urlPR, String urlRaketa, String loginPR, String passwordPR) {
        Order order = getOrderById(orderId);
        return SoapUtil.getDeliveryHistory(order.getRaketaId(), order.getPrId(), urlPR, urlRaketa, loginPR, passwordPR);
    }
}
