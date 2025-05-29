package com.example.CarSalesAgency.Controller;


import com.example.CarSalesAgency.DTO.UserUpdateDTO;
import com.example.CarSalesAgency.Entities.User;
import com.example.CarSalesAgency.Repository.UserRepository;
import com.example.CarSalesAgency.ServiceImplement.KeycloakAdminService;
import com.example.CarSalesAgency.ServiceImplement.KeycloakUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private KeycloakAdminService keycloakAdminService;
    private final KeycloakUserService keycloakUserService;
private final UserRepository userRepository;
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

    /**
     * Endpoint pour récupérer tous les utilisateurs
     */
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = keycloakUserService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Endpoint pour supprimer un utilisateur via son ID
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        keycloakUserService.deleteUserById(id);
        return ResponseEntity.ok("Utilisateur supprimé avec succès");
    }

    @PutMapping("/{userId}/role")
    public ResponseEntity<?> updateUserRole(@PathVariable String userId, @RequestParam String role) {
        keycloakAdminService.updateUserRole(userId, role);
        return ResponseEntity.ok("Rôle mis à jour");
    }



}
