//package com.MongoBoundary.models;
//
//import com.MongoBoundary.util.Constant;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.security.core.GrantedAuthority;
//
//import java.util.List;
//
//@Document(collection = Constant.USER_DB_NAME)
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class BoundaryUser {
//
//    @Id
//    private String id;
//
//    private String name;
//
//    private String email;
//
//    private String password;
//
//    private String address;
//
//    private Integer roleLevel;
//
//    private List<GrantedAuthority> authorities;
//
//    public BoundaryUser(BoundaryUser user){
//        this(user.id, user.name, user.email, user.password, user.address, user.roleLevel, user.authorities);
//    }
//
//}
