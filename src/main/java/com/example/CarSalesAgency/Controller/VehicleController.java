package com.example.CarSalesAgency.Controller;

import com.example.CarSalesAgency.Model.Vehicle;
import com.example.CarSalesAgency.Services.VehicleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
@Autowired
    VehicleInterface vehicleinterface;

    @PostMapping("add")
    public Vehicle addVehicle(@RequestBody Vehicle vehicle) {
        return vehicleinterface.addVehicle(vehicle);

    }

    @GetMapping("test")
    public String test(){
        return "test";
    }
}
