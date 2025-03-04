package com.example.CarSalesAgency.Controller;

import com.example.CarSalesAgency.Entities.Vehicule;
import com.example.CarSalesAgency.Repository.VehicleRepository;
import com.example.CarSalesAgency.Services.VehicleInterface;
import com.example.CarSalesAgency.enums.StatutVehicule;
import com.example.CarSalesAgency.enums.TypeVehicule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehiculeController {

    @Autowired
    private VehicleInterface vehicleInterface;

    @Autowired
    private VehicleRepository vehicleRepository;

    @PostMapping("/add")
    public Vehicule addVehicle(@RequestBody Vehicule vehicle) {
        return vehicleInterface.addVehicle(vehicle);
    }

    @PatchMapping("/updateVehicle/{idv}")
    public Vehicule updateVehicle(@PathVariable("idv") Long id, @RequestBody Vehicule vehicle) {
        return vehicleInterface.updateVehicle(id, vehicle);
    }

    @GetMapping("/getAllVehicule")
    public List<Vehicule> getAllVehicles() {
        return vehicleInterface.getAllVehicle();
    }

    @GetMapping("/getVehicleById/{id}")
    public Vehicule getVehicleById(@PathVariable Long id) {
        return vehicleInterface.getVehicleById(id);
    }

    @DeleteMapping("/deleteVehicle/{idv}")
    public void deleteVehicle(@PathVariable("idv") Long id) {
        vehicleInterface.deleteVehicle(id);
    }

    @PostMapping("/addListVehicle")
    public List<Vehicule> addListVehicles(@RequestBody List<Vehicule> vehicles) {
        return vehicleInterface.addListVehicles(vehicles);
    }

    @PutMapping("/{id}/type")
    public ResponseEntity<Vehicule> updateType(@PathVariable Long id, @RequestParam TypeVehicule type) {
        return ResponseEntity.ok(vehicleInterface.updateTypeVehicule(id, type));
    }

    @PutMapping("/{id}/statut")
    public ResponseEntity<Vehicule> updateStatut(@PathVariable Long id, @RequestParam StatutVehicule statut) {
        return ResponseEntity.ok(vehicleInterface.updateStatutVehicule(id, statut));
    }

    @GetMapping
    public ResponseEntity<List<Vehicule>> getVehicules(
            @RequestParam(required = false) TypeVehicule type,
            @RequestParam(required = false) StatutVehicule statut) {

        if (type != null && statut != null) {
            return ResponseEntity.ok(vehicleRepository.findByTypeVehiculeAndStatutVehicule(type, statut));
        } else if (type != null) {
            return ResponseEntity.ok(vehicleRepository.findByTypeVehicule(type));
        } else if (statut != null) {
            return ResponseEntity.ok(vehicleRepository.findByStatutVehicule(statut));
        }
        return ResponseEntity.ok(vehicleRepository.findAll());
    }
}
