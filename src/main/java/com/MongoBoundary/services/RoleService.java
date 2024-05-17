package com.MongoBoundary.services;

import com.MongoBoundary.models.BoundaryUser;
import com.MongoBoundary.models.Role;

import java.util.List;

public interface RoleService {

    Role addRole(Role role);

    Role updateRole(Role role);

    void deleteRole(Role role);

    void setRoleUser(BoundaryUser user, Integer roleLevel);

    void removeRoleUser(BoundaryUser user);

    List<Role> findAll();

}
