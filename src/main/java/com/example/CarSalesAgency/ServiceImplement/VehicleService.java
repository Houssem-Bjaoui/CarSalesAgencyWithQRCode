package com.example.CarSalesAgency.ServiceImplement;

import com.example.CarSalesAgency.Entities.Vehicule;
import com.example.CarSalesAgency.Repository.VehicleRepository;
import com.example.CarSalesAgency.Services.VehicleInterface;
import com.example.CarSalesAgency.enums.StatutVehicule;
import com.example.CarSalesAgency.enums.TypeVehicule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VehicleService implements VehicleInterface {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Vehicule addVehicle(Vehicule vehicle) {
        vehicle.setStatutVehicule(StatutVehicule.DISPONIBLE);
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicule updateVehicle(Long id, Vehicule vehicle) {
        Vehicule vehicule = vehicleRepository.findById(id).orElseThrow(() -> new RuntimeException("Véhicule non trouvé"));
        return vehicleRepository.save(vehicule);
    }

    @Override
    public List<Vehicule> getAllVehicle() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicule getVehicleById(Long id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    @Override
    public List<Vehicule> addListVehicles(List<Vehicule> listVehicles) {
        return vehicleRepository.saveAll(listVehicles);
    }

    @Override
    public Vehicule updateTypeVehicule(Long id, TypeVehicule newType) {
        Vehicule vehicule = vehicleRepository.findById(id).orElseThrow(() -> new RuntimeException("Véhicule non trouvé"));
        vehicule.setTypeVehicule(newType);
        return vehicleRepository.save(vehicule);
    }

    @Override
    public Vehicule updateStatutVehicule(Long id, StatutVehicule newStatut) {
        Vehicule vehicule = vehicleRepository.findById(id).orElseThrow(() -> new RuntimeException("Véhicule non trouvé"));
        vehicule.setStatutVehicule(newStatut);
        return vehicleRepository.save(vehicule);
    }
}