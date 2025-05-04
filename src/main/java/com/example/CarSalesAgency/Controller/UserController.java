package com.example.CarSalesAgency.Controller;

import com.example.CarSalesAgency.DTO.UserUpdateDTO;
import com.example.CarSalesAgency.Entities.User;
import com.example.CarSalesAgency.ServiceImplement.KeycloakUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final KeycloakUserService keycloakUserService;

    @GetMapping("/profile")
    public ResponseEntity<User> getProfile(Authentication authentication) {
        User user = keycloakUserService.getCurrentUser(authentication);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/updatep")
    public ResponseEntity<?> updateProfile(@RequestBody UserUpdateDTO dto, Authentication authentication) {
        User user = keycloakUserService.getCurrentUser(authentication);
        keycloakUserService.updateKeycloakUserProfile(user.getId(), dto);
        return ResponseEntity.ok("Profil mis à jour avec succès");
    }
}
