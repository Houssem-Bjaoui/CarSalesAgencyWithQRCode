package com.example.CarSalesAgency.Controller;

import com.example.CarSalesAgency.Entities.User;
import com.example.CarSalesAgency.ServiceImplement.KeycloakUserService;
import com.example.CarSalesAgency.Services.LikeInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/likes")
public class LikeController {

    @Autowired
    private LikeInterface likeInterface;

    @Autowired
    private KeycloakUserService keycloakUserService;

    @PostMapping("/{commentId}")
    public String toggleLike(
            Authentication authentication,
            @PathVariable Long commentId
    ) {
        User currentUser = keycloakUserService.getCurrentUser(authentication);
        return likeInterface.toggleLike(currentUser.getId(), commentId);
    }

    @GetMapping("/count/{commentId}")
    public long getLikesCount(@PathVariable Long commentId) {
        return likeInterface.getLikesCount(commentId);
    }
}