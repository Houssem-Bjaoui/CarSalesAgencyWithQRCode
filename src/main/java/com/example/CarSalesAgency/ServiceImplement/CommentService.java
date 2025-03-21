package com.example.CarSalesAgency.ServiceImplement;

import com.example.CarSalesAgency.Entities.Comment;
import com.example.CarSalesAgency.Entities.User;
import com.example.CarSalesAgency.Entities.Vehicule;
import com.example.CarSalesAgency.Repository.CommentRepository;
import com.example.CarSalesAgency.Repository.UserRepository;
import com.example.CarSalesAgency.Repository.VehiculeRepository;
import com.example.CarSalesAgency.Services.CommentInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements CommentInterface {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehiculeRepository vehiculeRepository; // Injecter VehiculeRepository

    @Override
    public Comment addComment(Comment comment) {
        // Validation de l'existence de l'utilisateur
        User user = userRepository.findById(comment.getUser().getId())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
        comment.setUser(user); // Associer l'utilisateur au commentaire

        // Validation de l'existence du véhicule
        Vehicule vehicule = vehiculeRepository.findById(comment.getVehicule().getId())
                .orElseThrow(() -> new RuntimeException("Véhicule introuvable"));
        comment.setVehicule(vehicule); // Associer le véhicule au commentaire

        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long id, String userId) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commentaire non trouvé"));

        // Vérifier que l'utilisateur authentifié est bien l'auteur du commentaire
        if (!comment.getUser().getId().equals(userId)) {
            throw new RuntimeException("Action non autorisée : vous n'êtes pas l'auteur de ce commentaire");
        }

        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> getCommentsByVehicule(Long vehiculeId) {
        return commentRepository.findByVehiculeId(vehiculeId);
    }

    @Override
    public List<Comment> getCommentsByUser(String userId) {
        return commentRepository.findByUserId(userId);
    }
}