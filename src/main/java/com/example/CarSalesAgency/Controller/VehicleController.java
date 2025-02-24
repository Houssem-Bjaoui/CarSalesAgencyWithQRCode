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
    public Vehicle addVehicle(@Valid @RequestBody Vehicle vehicle) {
        return vehicleinterface.addVehicle(vehicle);
    }
    @PatchMapping("/updateVehicle/{idv}")
    public Vehicle updateVehicle(@PathVariable ("idv")Long id, @RequestBody Vehicle vehicle) {
        return vehicleinterface.updateVehicle(id, vehicle);
    }

    @GetMapping("/getAllVehicule")
    public List<Vehicle> getAllVehicle() {
        return vehicleinterface.getAllVehicle();
    }

    @GetMapping("getVehicleById/{id}")
    public Vehicle getVehicleById(@PathVariable Long id) {
        return vehicleinterface.getVehicleById(id);
    }

    @DeleteMapping("/deleteVehicle/{idv}")
    public void deleteVehicle(@PathVariable("idv") Long id) {
         vehicleinterface.deleteVehicle(id);
    }
    @PostMapping("addlistVehicle")
    public List<Vehicle> addlistvehicles(@RequestBody List<Vehicle> vehicles)
    {
        return vehicleinterface.addListUsers(vehicles);
    }

}
