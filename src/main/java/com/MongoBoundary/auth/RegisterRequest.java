package com.MongoBoundary.auth;

import com.MongoBoundary.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstname;
    private String surname;
    private String email;
    private String password;
    private String address;
    private Integer roleLevel;
    private Role role;

}
