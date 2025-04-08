// AuthController.java
package com.example.CarSalesAgency.Controller;

import com.example.CarSalesAgency.Entities.*;
import com.example.CarSalesAgency.Services.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }



    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody LoginRequest loginRequest) {
        return authService.signIn(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody RegistrationRequest registrationRequest) {
        return authService.signUp(registrationRequest);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(@RequestParam String refreshToken) {
        return authService.logout(refreshToken);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return authService.getUsers();
    }




}