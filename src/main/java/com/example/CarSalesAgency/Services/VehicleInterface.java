package com.example.CarSalesAgency.Services;

import com.example.CarSalesAgency.Model.Vehicle;

import java.util.List;

public interface VehicleInterface {

    Vehicle addVehicle(Vehicle vehicle);
    void deleteVehicle(Long id);
    List<Vehicle> addListVehicle(List<Vehicle> listvehicle);
}
