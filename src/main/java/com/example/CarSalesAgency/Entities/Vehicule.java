package com.example.CarSalesAgency.Entities;

import com.example.CarSalesAgency.enums.StatutVehicule;
import com.example.CarSalesAgency.enums.TypeVehicule;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "vehicules")
@Data
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La marque est obligatoire")
    @Column(nullable = false)
    private String marque;

    @NotNull(message = "Le modèle est obligatoire")
    @Column(nullable = false)
    private String modele;

    @NotNull
    @Column(name = "annee_fabrication")
    @PositiveOrZero(message = "L'année doit être positive")
    private Integer anneeFabrication;

    @NotNull
    @PositiveOrZero(message = "Le prix doit être positif")
    private Double prix;

    @NotNull
    @PositiveOrZero(message = "Le kilométrage doit être positif")
    private Integer kilometrage;

    @NotNull
    @Column(name = "mise_en_circulation")
    private Integer miseEnCirculation;

    @NotNull
    private String energie;

    @NotNull
    @Column(name = "boite_vitesse")
    private String boiteVitesse;

    @NotNull
    @Column(name = "puissance_fiscale")
    @PositiveOrZero(message = "La puissance doit être positive")
    private Integer puissanceFiscale;

    @NotNull
    private String carrosserie;

    @Enumerated(EnumType.STRING)
    private TypeVehicule typeVehicule;

    @Enumerated(EnumType.STRING)
    private StatutVehicule statutVehicule;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Comment> comments;
}