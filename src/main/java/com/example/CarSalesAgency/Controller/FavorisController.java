package com.example.CarSalesAgency.Controller;


import com.example.CarSalesAgency.Entities.Favoris;
import com.example.CarSalesAgency.ServiceImplement.FavorisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("favoris")
public class FavorisController {

    @Autowired
    private FavorisService favorisService;

    @PostMapping("/add/{userId}/{vehiculeId}")
    public ResponseEntity<Favoris> addToFavoris(@PathVariable Long userId, @PathVariable Long vehiculeId) {
        Favoris favoris = favorisService.addToFavoris(userId, vehiculeId);
        return new ResponseEntity<>(favoris, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{userId}/{vehiculeId}")
    public ResponseEntity<Void> removeFromFavoris(@PathVariable Long userId, @PathVariable Long vehiculeId) {
        favorisService.removeFromFavoris(userId, vehiculeId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Favoris>> getFavorisByUser(@PathVariable Long userId) {
        List<Favoris> favoris = favorisService.getFavorisByUser(userId);
        return ResponseEntity.ok(favoris);
    }
}
