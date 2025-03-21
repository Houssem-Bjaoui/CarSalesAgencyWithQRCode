package com.example.CarSalesAgency.Services;


import com.example.CarSalesAgency.Entities.Comment;

import java.util.List;


public interface CommentInterface {
    Comment addComment(Comment comment);
    void deleteComment(Long id, String userId); // Ajout de l'ID utilisateur pour vérifier les permissions
    List<Comment> getCommentsByVehicule(Long vehiculeId);
    List<Comment> getCommentsByUser(String userId); // Changement de Long à String pour l'ID utilisateur
}

