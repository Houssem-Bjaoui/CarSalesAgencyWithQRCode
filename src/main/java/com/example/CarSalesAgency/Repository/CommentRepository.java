package com.example.CarSalesAgency.Repository;

import com.example.CarSalesAgency.Entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

   // List<Comment> findByVehiculeId(Long vehiculeId); // Trouver les commentaires d'un véhicule
    //List<Comment> findByUserId(Long userId); // Trouver les commentaires d'un utilisateur
}
