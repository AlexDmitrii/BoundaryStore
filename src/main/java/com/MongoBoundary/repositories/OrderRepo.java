package com.MongoBoundary.repositories;

import com.MongoBoundary.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface OrderRepo extends MongoRepository<Order, Integer> {
}
