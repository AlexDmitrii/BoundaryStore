package com.MongoBoundary.orderservice.service;

import com.MongoBoundary.orderservice.enums.Status;
import com.MongoBoundary.orderservice.event.OrderCreatedEvent;
import com.MongoBoundary.orderservice.model.Order;
import com.MongoBoundary.orderservice.model.ProductsList;
import com.MongoBoundary.orderservice.repository.OrderRepository;
import com.MongoBoundary.orderservice.util.SoapUtil;
import com.MongoBoundary.orderservice.util.Util;
//import com.MongoBoundary.productservice.model.Product;
//import com.MongoBoundary.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepo;

//    private final ProductRepository productRepo;

    private final MongoTemplate mongoTemplate;

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public Order createOrder(Order order) {
        try {
            orderRepo.save(order);
            kafkaTemplate.send("notificationTopic", new OrderCreatedEvent(order.getOrderId()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return order;
    }

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public List<Order> getOrdersByUserId(String userID) {
        return getOrderByUserId(userID);
    }

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

    public String updateOrderById(Map<String, Object> data, String orderId) {
        if (data != null && !data.isEmpty()) {
            Query query = Util.getQueryById(orderId);
            Update update = Util.fillDataForUpdate(data);

            mongoTemplate.updateFirst(query, update, Order.class, "order");

            return Status.returnStatus(Status.STATUS_SUCCESS);
        }

        return Status.returnStatus(Status.STATUS_ERROR);
    }

    public Order getOrderById(String orderId) {
        return orderRepo.findAll().stream()
                .filter(el -> el.getOrderId().equals(orderId))
                .findFirst()
                .orElse(null);
    }

    public List<Order> getOrderByUserId(String userID) {
        return orderRepo.findAll().stream()
                .filter(el -> el.getUserID() != null && el.getUserID().equals(userID))
                .toList();
    }

    public String getDeliveryHistoryByOrderId(String orderId, String urlPR, String urlRaketa, String loginPR, String passwordPR) {
        Order order = getOrderById(orderId);
        if (order != null) {
            if (order.getRaketaId() == null || order.getPrId() == null || order.getRaketaId().isEmpty() || order.getPrId().isEmpty())
                return "{\"status\": \"" + Status.STATUS_ERROR.getStatus() + "\"," +
                        "\"description\": \"raketaId or prId is empty\"}";
            return SoapUtil.getDeliveryHistory(order.getRaketaId(), order.getPrId(), urlPR, urlRaketa, loginPR, passwordPR);
        }
        return "{\"status\": \"" + Status.STATUS_ERROR.getStatus() + "\"," +
                "\"description\": \"Заказ №" + orderId + " не найден!\"}";
    }

//    public String linkProductsToOrder(String orderId, ProductsList productsList) {
//        List<Product> resultProductsIds = new ArrayList<>();
//        List<String> productIds = productsList.getProductsList();
//
//        for (String productId : productIds){
//            Product product = productRepo.findProductByProductId(productId);
//
//            if (product == null) continue;
//
//            resultProductsIds.add(product);
//        }
//
//        if (resultProductsIds.isEmpty())
//            return Status.returnStatus(Status.STATUS_NOT_FOUND);
//
//        Query query = Util.getQueryById(orderId);
//        Update update = new Update();
//        update.set("products", resultProductsIds);
//
//        mongoTemplate.updateFirst(query, update, Order.class, "order");
//
//        return Status.returnStatus(Status.STATUS_SUCCESS);
//
//    }
//
//    public List<Product> getProductsByOrderId(String orderId) {
//        Order order = orderRepo.findByOrderId(orderId);
//
//        if (order == null) return null;
//
//        return order.getProducts();
//    }


}
