package com.example.CarSalesAgency.DTO;

import com.example.CarSalesAgency.enums.TypeVehicule;
import lombok.Data;

import java.util.List;

@Data
public class VehiculeRequestDTO {
    private String marque;
    private String modele;
    private Integer anneeFabrication;
    private Double prix;
    private Integer kilometrage;
    private Integer miseEnCirculation;
    private String energie;
    private String boiteVitesse;
    private Integer puissanceFiscale;
    private String carrosserie;
    private String description;
    private TypeVehicule typeVehicule;
    private List<Long> featureIds;
}