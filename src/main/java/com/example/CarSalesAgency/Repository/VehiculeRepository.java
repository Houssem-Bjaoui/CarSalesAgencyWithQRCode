package com.example.CarSalesAgency.Repository;

import com.example.CarSalesAgency.Entities.Vehicule;
import com.example.CarSalesAgency.enums.StatutVehicule;
import com.example.CarSalesAgency.enums.TypeVehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {
    List<Vehicule> findByTypeVehicule(TypeVehicule type);
    List<Vehicule> findByStatutVehicule(StatutVehicule statut);
    List<Vehicule> findByTypeVehiculeAndStatutVehicule(TypeVehicule type, StatutVehicule statut);
}
