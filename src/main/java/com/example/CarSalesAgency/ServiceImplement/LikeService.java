package com.example.CarSalesAgency.ServiceImplement;

import com.example.CarSalesAgency.Entities.Comment;
import com.example.CarSalesAgency.Entities.Like;
import com.example.CarSalesAgency.Entities.User;
import com.example.CarSalesAgency.Repository.CommentRepository;
import com.example.CarSalesAgency.Repository.LikeRepository;
import com.example.CarSalesAgency.Repository.UserRepository;
import com.example.CarSalesAgency.Services.LikeInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService implements LikeInterface {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;


    @Override
    @Transactional
    public String toggleLike(Long userId, Long commentId) {
        // Vérifie si l'utilisateur a déjà liké ce commentaire
        if (likeRepository.existsByUser_IdAndComment_Id(userId, commentId)) {
            // Si le like existe, on le supprime (dislike)
            likeRepository.deleteByUser_IdAndComment_Id(userId, commentId);
            return "Like supprimé";
        } else {
            // Si l'utilisateur n'a pas encore liké, on ajoute un like
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
            Comment comment = commentRepository.findById(commentId)
                    .orElseThrow(() -> new RuntimeException("Commentaire introuvable"));

            // Création du like
            Like like = new Like();
            like.setUser(user);
            like.setComment(comment);

            // Sauvegarde du like en base de données
            likeRepository.save(like);
            return "Like ajouté";
        }
    }

    public long getLikesCount(Long commentId) {
        return likeRepository.countByComment_Id(commentId);
    }
}
