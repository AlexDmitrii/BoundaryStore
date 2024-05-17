package com.MongoBoundary.repositories;

import com.MongoBoundary.models.BoundaryUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<BoundaryUser, String> {

    BoundaryUser findUserByEmail(String email);
}
