package com.example.CarSalesAgency.Controller;

import com.example.CarSalesAgency.Services.LikeInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes") // URL de base pour les likes
public class LikeController {

    @Autowired
    private LikeInterface likeInterface;

    /**
     * Ajoute ou supprime un like sur un commentaire.
     *
     * @param userId    ID de l'utilisateur
     * @param commentId ID du commentaire
     * @return Message indiquant l'action effectuée
     */
    @PostMapping("/{userId}/{commentId}")
    public String likeOrUnlike(@PathVariable Long userId, @PathVariable Long commentId) {
        return likeInterface.toggleLike(userId, commentId);
    }

    /**
     * Récupère le nombre de likes d'un commentaire.
     *
     * @param commentId ID du commentaire
     * @return Nombre de likes
     */
    @GetMapping("/count/{commentId}")
    public long getLikesCount(@PathVariable Long commentId) {
        return likeInterface.getLikesCount(commentId);
    }
}

