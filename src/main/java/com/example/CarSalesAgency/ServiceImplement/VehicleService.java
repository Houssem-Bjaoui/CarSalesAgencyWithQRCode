package com.example.CarSalesAgency.ServiceImplement;

import com.example.CarSalesAgency.Entities.Vehicule;

import com.example.CarSalesAgency.Repository.VehicleRepository;

import com.example.CarSalesAgency.Services.VehicleInterface;
import com.example.CarSalesAgency.enums.StatutVehicule;
import com.example.CarSalesAgency.enums.TypeVehicule;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
public class VehicleService implements VehicleInterface {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private QRCodeService qrCodeService;

    @Override
    public Vehicule addVehicle(Vehicule vehicle) {
        vehicle.setStatutVehicule(StatutVehicule.DISPONIBLE);
        Vehicule savedVehicle = vehicleRepository.save(vehicle);
        try {
            // Générer et ajouter le QR Code pour le véhicule
            String qrCode = qrCodeService.generateQRCode(vehicle.getId().toString()); // Utilisez l'ID du véhicule pour le QR Code
            vehicle.setQrCode(qrCode);
        } catch (WriterException | IOException e) {
            // Gérer l'exception, par exemple en retournant une erreur spécifique ou un message de log
            throw new RuntimeException("Erreur lors de la génération du QR Code", e);
        }

        return vehicleRepository.save(savedVehicle);
    }


    @Override
    public Vehicule updateVehicle(Long id, Vehicule vehicle) {
        Vehicule existingVehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Véhicule non trouvé"));

        try {
            // Si le véhicule existe, mettez à jour le QR Code
            existingVehicle.setQrCode(qrCodeService.generateQRCode(existingVehicle.getId().toString()));
        } catch (WriterException | IOException e) {
            // Gérer l'exception
            throw new RuntimeException("Erreur lors de la génération du QR Code", e);
        }

        return vehicleRepository.save(existingVehicle);
    }


    @Override
    public List<Vehicule> getAllVehicle() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicule getVehicleById(Long id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    @Override
    public List<Vehicule> addListVehicles(List<Vehicule> listVehicles) {
        return vehicleRepository.saveAll(listVehicles);
    }

    @Override
    public Vehicule updateTypeVehicule(Long id, TypeVehicule newType) {
        Vehicule vehicule = vehicleRepository.findById(id).orElseThrow(() -> new RuntimeException("Véhicule non trouvé"));
        vehicule.setTypeVehicule(newType);
        return vehicleRepository.save(vehicule);
    }

    @Override
    public Vehicule updateStatutVehicule(Long id, StatutVehicule newStatut) {
        Vehicule vehicule = vehicleRepository.findById(id).orElseThrow(() -> new RuntimeException("Véhicule non trouvé"));
        vehicule.setStatutVehicule(newStatut);
        return vehicleRepository.save(vehicule);
    }
}