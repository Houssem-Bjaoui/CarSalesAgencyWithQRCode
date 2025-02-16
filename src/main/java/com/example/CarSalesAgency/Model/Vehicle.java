package com.example.CarSalesAgency.Model;

import jakarta.persistence.*;
import lombok.Data;


public class Vehicle {

    @Entity
    @Table(name = "vehicules")
    @Data
    public class Vehicule {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", updatable = false, nullable = false)
        private Long id;

        @Column(name = "marque", nullable = false)
        private String marque;

        @Column(name = "modele", nullable = false)
        private String modele;

        @Column(name = "annee_fabrication")
        private Integer anneeFabrication;

        @Column(name = "prix")
        private Double prix;

        @Column(name = "kilometrage")
        private Integer kilometrage;

        @Column(name = "mise_en_circulation")
        private Integer miseEnCirculation;

        @Column(name = "energie")
        private String energie;

        @Column(name = "boite_vitesse")
        private String boiteVitesse;

        @Column(name = "puissance_fiscale")
        private Integer puissanceFiscale;

        @Column(name = "carrosserie")
        private String carrosserie;
    }

}
