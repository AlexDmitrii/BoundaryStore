package com.MongoBoundary.repositories;

import com.MongoBoundary.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends MongoRepository<Order, Integer> {

    Order findByOrderId(String orderId);

}
