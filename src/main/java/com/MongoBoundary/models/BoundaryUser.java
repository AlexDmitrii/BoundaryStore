package com.MongoBoundary.models;

import com.MongoBoundary.enums.Role;
import com.MongoBoundary.util.Constant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Document(collection = Constant.USER_DB_NAME)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoundaryUser implements UserDetails {

    @Id
    private String id;

    private String firstname;

    private String surname;

    private String email;

    private String password;

    private String address;

    private Integer roleLevel;

    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Role.ADMIN.getAuthorities();
    }

    @Override
    public String getUsername() {
        return email;
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
