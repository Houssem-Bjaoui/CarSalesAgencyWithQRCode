package com.example.CarSalesAgency.DTO;

public class VehiculeSearchCriteria {
    private String marque;
    private String modele;
    private Integer anneeMin;
    private Integer anneeMax;
    private Double prixMin;
    private Double prixMax;
    private Integer kilometrageMin;
    private Integer kilometrageMax;
    private Integer miseEnCirculationMin;
    private Integer miseEnCirculationMax;
    private String energie;
    private String boiteVitesse;
    private Integer puissanceFiscaleMin;
    private Integer puissanceFiscaleMax;
    private String carrosserie;

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public Integer getAnneeMin() {
        return anneeMin;
    }

    public void setAnneeMin(Integer anneeMin) {
        this.anneeMin = anneeMin;
    }

    public Double getPrixMin() {
        return prixMin;
    }

    public void setPrixMin(Double prixMin) {
        this.prixMin = prixMin;
    }

    public Integer getAnneeMax() {
        return anneeMax;
    }

    public void setAnneeMax(Integer anneeMax) {
        this.anneeMax = anneeMax;
    }

    public Double getPrixMax() {
        return prixMax;
    }

    public void setPrixMax(Double prixMax) {
        this.prixMax = prixMax;
    }

    public Integer getKilometrageMin() {
        return kilometrageMin;
    }

    public void setKilometrageMin(Integer kilometrageMin) {
        this.kilometrageMin = kilometrageMin;
    }

    public Integer getKilometrageMax() {
        return kilometrageMax;
    }

    public void setKilometrageMax(Integer kilometrageMax) {
        this.kilometrageMax = kilometrageMax;
    }

    public Integer getMiseEnCirculationMin() {
        return miseEnCirculationMin;
    }

    public void setMiseEnCirculationMin(Integer miseEnCirculationMin) {
        this.miseEnCirculationMin = miseEnCirculationMin;
    }

    public Integer getMiseEnCirculationMax() {
        return miseEnCirculationMax;
    }

    public void setMiseEnCirculationMax(Integer miseEnCirculationMax) {
        this.miseEnCirculationMax = miseEnCirculationMax;
    }

    public String getEnergie() {
        return energie;
    }

    public void setEnergie(String energie) {
        this.energie = energie;
    }

    public String getBoiteVitesse() {
        return boiteVitesse;
    }

    public void setBoiteVitesse(String boiteVitesse) {
        this.boiteVitesse = boiteVitesse;
    }

    public Integer getPuissanceFiscaleMin() {
        return puissanceFiscaleMin;
    }

    public void setPuissanceFiscaleMin(Integer puissanceFiscaleMin) {
        this.puissanceFiscaleMin = puissanceFiscaleMin;
    }

    public Integer getPuissanceFiscaleMax() {
        return puissanceFiscaleMax;
    }

    public void setPuissanceFiscaleMax(Integer puissanceFiscaleMax) {
        this.puissanceFiscaleMax = puissanceFiscaleMax;
    }

    public String getCarrosserie() {
        return carrosserie;
    }

    public void setCarrosserie(String carrosserie) {
        this.carrosserie = carrosserie;
    }
}
