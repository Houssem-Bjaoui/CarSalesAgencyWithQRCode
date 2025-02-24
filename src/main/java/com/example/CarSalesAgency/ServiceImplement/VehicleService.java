package com.example.CarSalesAgency.ServiceImplement;

import com.example.CarSalesAgency.Model.Vehicle;
import com.example.CarSalesAgency.Repository.VehicleRepository;
import com.example.CarSalesAgency.Services.VehicleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService implements VehicleInterface {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Vehicle addVehicle(Vehicle vehicle) {

        return vehicleRepository.save(vehicle);
    }
    @Override
    public Vehicle updateVehicle(Long id, Vehicle vehicle) {
        Vehicle vehicule = vehicleRepository.findById(id).get();
        vehicule.setMarque(vehicle.getMarque());
        vehicule.setModele(vehicle.getModele());
        vehicule.setEnergie(vehicle.getEnergie());
        vehicule.setBoiteVitesse(vehicle.getBoiteVitesse());
        vehicule.setCarrosserie(vehicle.getCarrosserie());
        vehicule.setAnneeFabrication(vehicle.getAnneeFabrication());
        vehicule.setKilometrage(vehicle.getKilometrage());
        vehicule.setPuissanceFiscale(vehicle.getPuissanceFiscale());
        vehicule.setPrix(vehicle.getPrix());
        return vehicleRepository.save(vehicule);
    }

    @Override
    public List<Vehicle> getAllVehicle() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id).orElse(null);
    }





}
