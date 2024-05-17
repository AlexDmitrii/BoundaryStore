package com.MongoBoundary.services.impl;

import com.MongoBoundary.models.BoundaryUser;
import com.MongoBoundary.repositories.UserRepo;
import com.MongoBoundary.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRepoUserDetailsService implements UserDetailsService {

    final UserRepo userRepo;

    final RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        BoundaryUser user = userRepo.findUserByEmail(email);

        if(user == null) {
            throw new UsernameNotFoundException(email);
        }

        return new BoundaryUserDetails(user);
    }

    static final class BoundaryUserDetails extends BoundaryUser implements UserDetails{

        public BoundaryUserDetails(BoundaryUser user) {
            super(user);
        }

        @Override
        public String getUsername() {
            return this.getEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }


    }
}
