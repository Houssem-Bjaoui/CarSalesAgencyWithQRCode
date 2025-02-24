package com.example.CarSalesAgency.Controller;

import com.example.CarSalesAgency.Model.Vehicle;
import com.example.CarSalesAgency.Services.VehicleInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    @Autowired
    private VehicleInterface vehicleinterface;

    @PostMapping("/add")
    public Vehicle addVehicle(@Valid @RequestBody Vehicle vehicle) {
        return vehicleinterface.addVehicle(vehicle);
    }
}
