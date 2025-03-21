package com.example.CarSalesAgency.Controller;
import com.example.CarSalesAgency.Entities.Comment;
import com.example.CarSalesAgency.Entities.User;
import com.example.CarSalesAgency.Entities.Vehicule;
import com.example.CarSalesAgency.Repository.VehiculeRepository;
import com.example.CarSalesAgency.ServiceImplement.KeycloakUserService;
import com.example.CarSalesAgency.Services.CommentInterface;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentInterface commentInterface;

    @Autowired
    private KeycloakUserService keycloakUserService;

    @Autowired
    private VehiculeRepository vehiculeRepository; // Injecter VehiculeRepository

    @PostMapping("/add")
    public ResponseEntity<Comment> addComment(
            Authentication authentication,
            @RequestBody @Valid Comment comment
    ) {
        // Récupérer l'utilisateur authentifié
        User currentUser = keycloakUserService.getCurrentUser(authentication);
        comment.setUser(currentUser);

        // Vérifier que le véhicule est fourni
        if (comment.getVehicule() == null || comment.getVehicule().getId() == null) {
            throw new RuntimeException("Véhicule non spécifié");
        }

        // Associer le véhicule
        Vehicule vehicule = vehiculeRepository.findById(comment.getVehicule().getId())
                .orElseThrow(() -> new RuntimeException("Véhicule introuvable"));
        comment.setVehicule(vehicule);

        return ResponseEntity.ok(commentInterface.addComment(comment));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteComment(
            Authentication authentication,
            @PathVariable Long id
    ) {
        User currentUser = keycloakUserService.getCurrentUser(authentication);
        commentInterface.deleteComment(id, currentUser.getId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/vehicule/{vehiculeId}")
    public ResponseEntity<List<Comment>> getCommentsByVehicule(@PathVariable Long vehiculeId) {
        return ResponseEntity.ok(commentInterface.getCommentsByVehicule(vehiculeId));
    }

    @GetMapping("/me")
    public ResponseEntity<List<Comment>> getMyComments(Authentication authentication) {
        User currentUser = keycloakUserService.getCurrentUser(authentication);
        return ResponseEntity.ok(commentInterface.getCommentsByUser(currentUser.getId()));
    }
}
