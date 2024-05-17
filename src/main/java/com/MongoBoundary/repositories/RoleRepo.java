package com.MongoBoundary.repositories;

import com.MongoBoundary.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RoleRepo extends MongoRepository<Role, Integer> {

    Role findRoleByLevel(int level);

}
