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
    private VehicleInterface vehicleinterface;

    @Autowired
    private VehicleRepository vehicleRepository;

    @PostMapping("/add")
    public Vehicule addVehicle(@RequestBody Vehicule vehicle) {
        return vehicleinterface.addVehicle(vehicle);
    }

    @PatchMapping("/updateVehicle/{idv}")
    public Vehicule updateVehicle(@PathVariable("idv") Long id, @RequestBody Vehicule vehicle) {
        return vehicleinterface.updateVehicle(id, vehicle);
    }

    @GetMapping("/getAllVehicule")
    public List<Vehicule> getAllVehicle() {
        return vehicleinterface.getAllVehicle();
    }

    @GetMapping("getVehicleById/{id}")
    public Vehicule getVehicleById(@PathVariable Long id) {
        return vehicleinterface.getVehicleById(id);
    }

    @DeleteMapping("/deleteVehicle/{idv}")
    public void deleteVehicle(@PathVariable("idv") Long id) {
        vehicleinterface.deleteVehicle(id);
    }

    @PostMapping("addlistVehicle")
    public List<Vehicule> addlistvehicles(@RequestBody List<Vehicule> vehicles) {
        return vehicleinterface.addListVehicles(vehicles);
    }

    // Mettre à jour le type via paramètre de requête
    @PutMapping("/{id}/type")
    public ResponseEntity<Vehicule> updateType(
            @PathVariable Long id,
            @RequestParam TypeVehicule type
    ) {
        return ResponseEntity.ok(
                vehicleinterface.updateTypeVehicule(id, type)
        );
    }

    // Mettre à jour le statut via paramètre de requête
    @PutMapping("/{id}/statut")
    public ResponseEntity<Vehicule> updateStatut(
            @PathVariable Long id,
            @RequestParam StatutVehicule statut
    ) {
        return ResponseEntity.ok(
                vehicleinterface.updateStatutVehicule(id, statut)
        );
    }

    // Lister tous les véhicules OU filtrer par type et statut
    @GetMapping
    public ResponseEntity<List<Vehicule>> getVehicules(
            @RequestParam(required = false) TypeVehicule type,
            @RequestParam(required = false) StatutVehicule statut
    ) {
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