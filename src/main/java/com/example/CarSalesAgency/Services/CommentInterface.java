package com.example.CarSalesAgency.Services;


import com.example.CarSalesAgency.Entities.Comment;



public interface CommentInterface {

    Comment addComment(Comment comment);

    void deleteComment(Long id);
}

