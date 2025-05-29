package com.example.CarSalesAgency.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



    @Entity
    @Getter
    @Setter
    @Table(name = "user_dislike", uniqueConstraints = {
            @UniqueConstraint(columnNames = {"user_id", "comment_id"})
    })
    public class Dislike {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "comment_id", referencedColumnName = "id")
        private Comment comment;

        @ManyToOne
        @JoinColumn(name = "user_id", referencedColumnName = "id")
        private User user;
    }


