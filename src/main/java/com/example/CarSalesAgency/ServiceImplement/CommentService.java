package com.example.CarSalesAgency.ServiceImplement;

import com.example.CarSalesAgency.Entities.Comment;
import com.example.CarSalesAgency.Repository.CommentRepository;
import com.example.CarSalesAgency.Services.CommentInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements CommentInterface {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Commentaire non trouvé !");
        }
    }

    @Override
    public List<Comment> getCommentsByVehicule(Long vehiculeId) {
        return commentRepository.findByVehiculeId(vehiculeId);
    }

    @Override
    public List<Comment> getCommentsByUser(Long userId) {
        return commentRepository.findByUserId(userId);
    }
}
