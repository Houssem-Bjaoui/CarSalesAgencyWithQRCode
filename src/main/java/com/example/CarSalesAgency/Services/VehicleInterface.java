package com.example.CarSalesAgency.Services;


import com.example.CarSalesAgency.Entities.Vehicule;

import com.example.CarSalesAgency.enums.StatutVehicule;
import com.example.CarSalesAgency.enums.TypeVehicule;

import java.util.List;

public interface VehicleInterface {
    Vehicule addVehicle(Vehicule vehicle);
    Vehicule updateVehicle(Long id, Vehicule vehicle);
    List<Vehicule> getAllVehicle();
    Vehicule getVehicleById(Long id);
    void deleteVehicle(Long id);
    List<Vehicule> addListVehicles(List<Vehicule> listVehicles);
    Vehicule updateTypeVehicule(Long id, TypeVehicule newType);
    Vehicule updateStatutVehicule(Long id, StatutVehicule newStatut);
}


