package com.example.CarSalesAgency.ServiceImplement;

import com.example.CarSalesAgency.Model.Vehicle;
import com.example.CarSalesAgency.Repository.VehicleRepository;
import com.example.CarSalesAgency.Services.VehicleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService implements VehicleInterface {
    @Autowired
    VehicleRepository vehicleRepository;
    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);

    }
}
