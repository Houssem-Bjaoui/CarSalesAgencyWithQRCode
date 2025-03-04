package com.example.CarSalesAgency.Controller;
import com.example.CarSalesAgency.Entities.Comment;
import com.example.CarSalesAgency.Services.CommentInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentInterface commentInterface;

    @PostMapping("/add")
    public ResponseEntity<Comment> addComment(@RequestBody @Valid Comment comment) {
        return ResponseEntity.ok(commentInterface.addComment(comment));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentInterface.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/vehicule/{vehiculeId}")
    public ResponseEntity<List<Comment>> getCommentsByVehicule(@PathVariable Long vehiculeId) {
        return ResponseEntity.ok(commentInterface.getCommentsByVehicule(vehiculeId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Comment>> getCommentsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(commentInterface.getCommentsByUser(userId));
    }
}

