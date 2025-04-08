package com.example.CarSalesAgency.Controller;

import com.example.CarSalesAgency.Entities.Favoris;
import com.example.CarSalesAgency.Entities.User;
import com.example.CarSalesAgency.ServiceImplement.FavorisService;
import com.example.CarSalesAgency.ServiceImplement.KeycloakUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("favoris")
public class FavorisController {

    @Autowired
    private FavorisService favorisService;

    @Autowired
    private KeycloakUserService keycloakUserService;

    @PostMapping("/add/{vehiculeId}")
    public ResponseEntity<Favoris> addToFavoris(Authentication authentication, @PathVariable Long vehiculeId) {
        User currentUser = keycloakUserService.getCurrentUser(authentication);
        Favoris favoris = favorisService.addToFavoris(currentUser.getId(), vehiculeId);
        return new ResponseEntity<>(favoris, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{vehiculeId}")
    public ResponseEntity<Void> removeFromFavoris(Authentication authentication, @PathVariable Long vehiculeId) {
        User currentUser = keycloakUserService.getCurrentUser(authentication);
        favorisService.removeFromFavoris(currentUser.getId(), vehiculeId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me")
    public ResponseEntity<List<Favoris>> getMyFavoris(Authentication authentication) {
        User currentUser = keycloakUserService.getCurrentUser(authentication);
        List<Favoris> favoris = favorisService.getFavorisByUser(currentUser.getId());
        return ResponseEntity.ok(favoris);
    }

    // Pour les administrateurs seulement
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Favoris>> getFavorisByUser(@PathVariable String userId) {
        List<Favoris> favoris = favorisService.getFavorisByUser(userId);
        return ResponseEntity.ok(favoris);
    }
}