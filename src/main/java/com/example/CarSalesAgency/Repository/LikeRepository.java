package com.example.CarSalesAgency.Repository;

import com.example.CarSalesAgency.Entities.Comment;
import com.example.CarSalesAgency.Entities.Like;
import com.example.CarSalesAgency.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    // Trouver un like par utilisateur et commentaire
    Optional<Like> findByUserAndComment(User user, Comment comment);

    // Compter les likes d'un commentaire
    int countByComment(Comment comment);
}
