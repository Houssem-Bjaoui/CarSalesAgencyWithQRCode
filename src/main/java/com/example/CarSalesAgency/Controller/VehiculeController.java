package com.example.CarSalesAgency.Controller;

import com.example.CarSalesAgency.Entities.Vehicule;
import com.example.CarSalesAgency.ServiceImplement.QRCodeService;
import com.example.CarSalesAgency.Services.VehicleInterface;
import com.example.CarSalesAgency.enums.StatutVehicule;
import com.example.CarSalesAgency.enums.TypeVehicule;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/vehicules")
public class VehiculeController {

    @Autowired
    private VehicleInterface vehicleInterface;



    @Autowired
    private QRCodeService qrCodeService;

    @PostMapping("/add")
    public Vehicule addVehicle(@RequestBody Vehicule vehicle) {
        return vehicleInterface.addVehicle(vehicle);
    }

    @PatchMapping("/updateVehicule/{idv}")
    public Vehicule updateVehicle(@PathVariable("idv") Long id, @RequestBody Vehicule vehicle) {
        return vehicleInterface.updateVehicle(id, vehicle);
    }

    @GetMapping("/getAllVehicule")
    public List<Vehicule> getAllVehicles() {
        return vehicleInterface.getAllVehicle();
    }

    @GetMapping("/getVehiculeById/{id}")
    public Vehicule getVehicleById(@PathVariable Long id) {
        return vehicleInterface.getVehicleById(id);
    }

    @DeleteMapping("/deleteVehicule/{idv}")
    public void deleteVehicle(@PathVariable("idv") Long id) {
        vehicleInterface.deleteVehicle(id);
    }

    @PostMapping("/addListVehicule")
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

    @GetMapping("/getVehicleQRCode/{id}")
    public ResponseEntity<String> getVehicleQRCode(@PathVariable Long id) {
        // Récupérer le véhicule par ID
        Vehicule vehicule = vehicleInterface.getVehicleById(id);
        if (vehicule == null) {
            return ResponseEntity.notFound().build();
        }

        // Si le QR code est null ou vide, on le génère
        if (vehicule.getQrCode() == null || vehicule.getQrCode().isEmpty()) {
            try {
                String qrCode = qrCodeService.generateQRCode(vehicule.getId().toString());
                vehicule.setQrCode(qrCode);
                // Vous pouvez aussi sauvegarder le QR code dans votre base de données ici
            } catch (WriterException | IOException e) {
                // Gérer l'exception en fonction de votre logique d'erreur
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la génération du QR code.");
            }
        }

        // Retourner le QR Code généré (ou l'URL en Base64)
        return ResponseEntity.ok(vehicule.getQrCode());
    }
}
