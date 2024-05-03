package com.MongoBoundary.repositories;

import com.MongoBoundary.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepo extends MongoRepository<Order, Integer> {
}
