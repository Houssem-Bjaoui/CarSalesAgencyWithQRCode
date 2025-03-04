package com.example.CarSalesAgency.Services;


import com.example.CarSalesAgency.Entities.Comment;

import java.util.List;


public interface CommentInterface {
    Comment addComment(Comment comment);
    void deleteComment(Long id);
    List<Comment> getCommentsByVehicule(Long vehiculeId);
    List<Comment> getCommentsByUser(Long userId);
}

