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
        commentRepository.deleteById(id);

    }
}
