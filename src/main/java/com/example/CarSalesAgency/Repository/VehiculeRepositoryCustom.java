package com.example.CarSalesAgency.Repository;

import com.example.CarSalesAgency.DTO.VehiculeSearchCriteria;
import com.example.CarSalesAgency.Entities.Vehicule;

import java.util.List;

public interface VehiculeRepositoryCustom {
    List<Vehicule> searchByCriteria(VehiculeSearchCriteria criteria);
}
