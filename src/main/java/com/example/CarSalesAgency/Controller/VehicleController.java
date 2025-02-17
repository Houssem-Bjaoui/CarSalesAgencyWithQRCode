package com.example.CarSalesAgency.Controller;

import com.example.CarSalesAgency.Model.Vehicle;
import com.example.CarSalesAgency.Services.VehicleInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    @Autowired
    private VehicleInterface vehicleinterface;

    @PostMapping("/add")
    public Vehicle addVehicle(@RequestBody Vehicle vehicle) {
        return vehicleinterface.addVehicle(vehicle);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteVehicle(@PathVariable Long id) {
        vehicleinterface.deleteVehicle(id);
    }

    @PostMapping("addList")
    public List<Vehicle> addListVehicle(@RequestBody List<Vehicle> listvehicle) {
        return vehicleinterface.addListVehicle(listvehicle);
    }


}
