package com.MongoBoundary.MongoBoundary.Repository;

import com.MongoBoundary.MongoBoundary.Model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepo extends MongoRepository<Order, Integer> {
}
