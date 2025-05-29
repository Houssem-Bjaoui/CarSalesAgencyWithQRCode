package com.example.CarSalesAgency.ServiceImplement;

import com.example.CarSalesAgency.Entities.Comment;
import com.example.CarSalesAgency.Entities.Dislike;
import com.example.CarSalesAgency.Entities.User;
import com.example.CarSalesAgency.Repository.CommentRepository;
import com.example.CarSalesAgency.Repository.DislikeRepository;
import com.example.CarSalesAgency.Repository.LikeRepository;
import com.example.CarSalesAgency.Repository.UserRepository;
import com.example.CarSalesAgency.Services.DislikeInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DislikeService implements DislikeInterface {

    @Autowired
    private DislikeRepository dislikeRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    @Transactional
    public String toggleDislike(String userId, Long commentId) {
        if (dislikeRepository.existsByUser_IdAndComment_Id(userId, commentId)) {
            dislikeRepository.deleteByUser_IdAndComment_Id(userId, commentId);
            return "Dislike supprimé";
        } else {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
            Comment comment = commentRepository.findById(commentId)
                    .orElseThrow(() -> new RuntimeException("Commentaire introuvable"));

            // Supprimer le like si existant
            if (likeRepository.existsByUser_IdAndComment_Id(userId, commentId)) {
                likeRepository.deleteByUser_IdAndComment_Id(userId, commentId);
            }

            Dislike dislike = new Dislike();
            dislike.setUser(user);
            dislike.setComment(comment);

            dislikeRepository.save(dislike);
            return "Dislike ajouté";
        }
    }

    @Override
    public long getDislikesCount(Long commentId) {
        return dislikeRepository.countByComment_Id(commentId);
    }

}
