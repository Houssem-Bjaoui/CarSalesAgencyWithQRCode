package com.example.CarSalesAgency.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Entity
@Table(name = "vehicules")
@Data
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La marque est obligatoire")
    @Column(nullable = false)
    private String marque;

    @NotNull(message = "Le modèle est obligatoire")
    @Column(nullable = false)
    private String modele;

    @Column(name = "annee_fabrication")
    @PositiveOrZero(message = "L'année doit être positive")
    private Integer anneeFabrication;

    @PositiveOrZero(message = "Le prix doit être positif")
    private Double prix;

    @PositiveOrZero(message = "Le kilométrage doit être positif")
    private Integer kilometrage;

    @Column(name = "mise_en_circulation")
    private Integer miseEnCirculation;

    private String energie;

    @Column(name = "boite_vitesse")
    private String boiteVitesse;

    @Column(name = "puissance_fiscale")
    @PositiveOrZero(message = "La puissance doit être positive")
    private Integer puissanceFiscale;

    private String carrosserie;
}