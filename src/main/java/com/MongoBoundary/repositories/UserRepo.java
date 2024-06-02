package com.MongoBoundary.repositories;

import com.MongoBoundary.models.BoundaryUser;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<BoundaryUser, String> {

    Optional<BoundaryUser> findByEmail(String email);
//    BoundaryUser findUserByEmail(String email);
    boolean existsByEmail(String email);
}
