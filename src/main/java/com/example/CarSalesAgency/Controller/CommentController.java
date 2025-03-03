package com.example.CarSalesAgency.Controller;
import com.example.CarSalesAgency.Entities.Comment;
import com.example.CarSalesAgency.Services.CommentInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentInterface commentInterface;

    @PostMapping("/add")
    public Comment addComment(@RequestBody Comment comment) {
        return commentInterface.addComment(comment);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentInterface.deleteComment(id);
    }
}
