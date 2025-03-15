package com.example.CarSalesAgency.Services;

import com.example.CarSalesAgency.Entities.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;

public interface AuthService {
    ResponseEntity<?> signIn(LoginRequest loginRequest);
    ResponseEntity<?> signUp(RegistrationRequest registrationRequest);
    ResponseEntity<?> logout(String refreshToken);
    ResponseEntity<?> refreshToken(RefreshTokenRequest refreshTokenRequest);
    ResponseEntity<?> getUsers();
}