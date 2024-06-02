package com.MongoBoundary.token;

import com.MongoBoundary.models.BoundaryUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Token {

    @Id
    public String id;
    public String token;
    public TokenType tokenType = TokenType.BEARER;
    public boolean expired;
    public boolean revoked;
    public BoundaryUser user;

}
