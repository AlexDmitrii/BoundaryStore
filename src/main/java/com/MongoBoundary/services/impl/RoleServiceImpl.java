package com.MongoBoundary.services.impl;

import com.MongoBoundary.enums.RoleEnum;
import com.MongoBoundary.models.BoundaryUser;
import com.MongoBoundary.models.Role;
import com.MongoBoundary.repositories.RoleRepo;
import com.MongoBoundary.repositories.UserRepo;
import com.MongoBoundary.services.RoleService;
import com.MongoBoundary.util.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    final UserRepo userRepo;

    final RoleRepo roleRepo;

    @Override
    public Role addRole(Role role) {
        roleRepo.save(role);

        return role;
    }

    @Override
    public Role updateRole(Role role) {
        Role findedRole = roleRepo.findRoleByLevel(role.getLevel());

        if (findedRole != null) {
            roleRepo.save(findedRole);
            return findedRole;
        }

        return null;
    }

    @Override
    public void deleteRole(Role role) {
        roleRepo.delete(role);
    }

    @Override
    public void setRoleUser(BoundaryUser user, Integer roleLevel) {
        user.setRoleLevel(roleLevel);
    }

    @Override
    public void removeRoleUser(BoundaryUser user) {
        user.setRoleLevel(RoleEnum.ROLE_USER.getRoleLevel());
    }

    @Override
    public List<Role> findAll() {
        return roleRepo.findAll();
    }

}
