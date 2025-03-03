package com.example.CarSalesAgency.Entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="Comments")
@Data
public class Comment {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

    @NotNull(message = "Le contenu est obligatoire")
    @Size(max = 500, message = "Le commentaire ne doit pas dépasser 500 caractères")
   private String comment;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @ManyToOne
    private Vehicule vehicule;

    @OneToMany(mappedBy = "comment" , cascade = CascadeType.ALL)
    private List<Like> Likes;
}
