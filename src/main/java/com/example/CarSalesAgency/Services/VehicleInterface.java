package com.example.CarSalesAgency.Services;

import com.example.CarSalesAgency.Model.Vehicle;

import java.util.List;

public interface VehicleInterface {


        Vehicle addVehicle(Vehicle vehicle);
        Vehicle updateVehicle(Long id, Vehicle vehicle);
        List<Vehicle> getAllVehicle();
        Vehicle getVehicleById(Long id);
    }


