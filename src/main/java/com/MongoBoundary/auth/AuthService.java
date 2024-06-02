package com.MongoBoundary.auth;

import com.MongoBoundary.models.BoundaryUser;
import com.MongoBoundary.repositories.UserRepo;
import com.MongoBoundary.security.JWTService;
import com.MongoBoundary.token.Token;
import com.MongoBoundary.token.TokenRepo;
import com.MongoBoundary.token.TokenType;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;

    private final JWTService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final TokenRepo tokenRepo;

    public AuthenticationResponse register(RegisterRequest regRequest){

        var user = BoundaryUser.builder()
                .firstname(regRequest.getFirstname())
                .surname(regRequest.getSurname())
                .email(regRequest.getEmail())
                .password(passwordEncoder.encode(regRequest.getPassword()))
                .role(regRequest.getRole())
                .build();

        var savedUser = userRepo.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest authRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()
                )
        );
        var user = userRepo.findByEmail(authRequest.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var jwtRefreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(jwtRefreshToken)
                .build();
    }

    private void revokeAllUserTokens(BoundaryUser user) {
        var validTokensByUser = tokenRepo.findAllByUser(user);
        if (validTokensByUser.isEmpty())
            return;

        validTokensByUser.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });

        tokenRepo.saveAll(validTokensByUser);
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;

        if (authHeader == null || !authHeader.contains("Bearer "))
            return;

        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);

        if (userEmail == null) return;

        var user = userRepo.findByEmail(userEmail).orElseThrow();
        if (jwtService.isTokenValid(refreshToken, user)){
            var accessToken = jwtService.generateToken(user);
            revokeAllUserTokens(user);
            saveUserToken(user, accessToken);

            var authResponse = AuthenticationResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
            new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
        }
    }

    public void saveUserToken(BoundaryUser user, String jwtToken){
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .build();
        tokenRepo.save(token);
    }

}
