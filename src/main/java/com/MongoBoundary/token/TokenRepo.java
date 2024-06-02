package com.MongoBoundary.token;

import com.MongoBoundary.models.BoundaryUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepo extends MongoRepository<Token, String> {

    List<Token> findAllByUser(BoundaryUser user);

    Optional<Token> findByToken(String token);

}
