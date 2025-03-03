package com.example.CarSalesAgency.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user_like")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "like_count")
    private int like;

    @ManyToOne
    @JoinColumn(name = "comment_id", referencedColumnName = "id")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id") // Colonne de jointure dans la table `like`
    private User user;
}