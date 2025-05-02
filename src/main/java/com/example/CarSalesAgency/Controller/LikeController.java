package com.example.CarSalesAgency.Controller;

import com.example.CarSalesAgency.Entities.User;
import com.example.CarSalesAgency.ServiceImplement.KeycloakUserService;
import com.example.CarSalesAgency.Services.LikeInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/likes")
public class LikeController {

    @Autowired
    private LikeInterface likeInterface;

    @Autowired
    private KeycloakUserService keycloakUserService;

    @PostMapping("/{commentId}")
    public ResponseEntity<Map<String, String>> toggleLike(
            Authentication authentication,
            @PathVariable Long commentId
    ) {
        User currentUser = keycloakUserService.getCurrentUser(authentication);
        String result = likeInterface.toggleLike(currentUser.getId(), commentId);

        return ResponseEntity.ok(Map.of("message", result));
    }


    @GetMapping("/count/{commentId}")
    public long getLikesCount(@PathVariable Long commentId) {
        return likeInterface.getLikesCount(commentId);
    }
}