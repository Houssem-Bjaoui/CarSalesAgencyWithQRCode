package com.example.CarSalesAgency.ServiceImplement;

import com.example.CarSalesAgency.Entities.Feature;
import com.example.CarSalesAgency.Entities.Vehicule;
import com.example.CarSalesAgency.Repository.VehiculeRepository;
import com.example.CarSalesAgency.Services.FeatureInterface;
import com.example.CarSalesAgency.Services.VehicleInterface;
import com.example.CarSalesAgency.enums.StatutVehicule;
import com.example.CarSalesAgency.enums.TypeVehicule;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Service
public class VehicleService implements VehicleInterface {

    @Autowired
    private VehiculeRepository vehicleRepository;

    @Autowired
    private QRCodeService qrCodeService;

    @Autowired
    private FeatureInterface featureService;

    @Override
    public Vehicule addVehicle(Vehicule vehicle) {
        vehicle.setStatutVehicule(StatutVehicule.DISPONIBLE);
        Vehicule savedVehicle = vehicleRepository.save(vehicle);
        try {
            // Générer et ajouter le QR Code pour le véhicule
            String qrCode = qrCodeService.generateQRCode(vehicle.getId().toString());
            vehicle.setQrCode(qrCode);
        } catch (WriterException | IOException e) {
            throw new RuntimeException("Erreur lors de la génération du QR Code", e);
        }

        return vehicleRepository.save(savedVehicle);
    }

    @Override
    public Vehicule updateVehicle(Long id, Vehicule vehicle) {
        Vehicule existingVehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Véhicule non trouvé"));

        // Mettre à jour les propriétés du véhicule existant
        existingVehicle.setMarque(vehicle.getMarque());
        existingVehicle.setModele(vehicle.getModele());
        existingVehicle.setAnneeFabrication(vehicle.getAnneeFabrication());
        existingVehicle.setPrix(vehicle.getPrix());
        existingVehicle.setKilometrage(vehicle.getKilometrage());
        existingVehicle.setMiseEnCirculation(vehicle.getMiseEnCirculation());
        existingVehicle.setEnergie(vehicle.getEnergie());
        existingVehicle.setBoiteVitesse(vehicle.getBoiteVitesse());
        existingVehicle.setPuissanceFiscale(vehicle.getPuissanceFiscale());
        existingVehicle.setCarrosserie(vehicle.getCarrosserie());
        existingVehicle.setTypeVehicule(vehicle.getTypeVehicule());
        existingVehicle.setDescription(vehicle.getDescription());

        // Si des features sont fournies, les mettre à jour
        if (vehicle.getFeatures() != null && !vehicle.getFeatures().isEmpty()) {
            existingVehicle.setFeatures(vehicle.getFeatures());
        }

        try {
            // Mettre à jour le QR Code
            existingVehicle.setQrCode(qrCodeService.generateQRCode(existingVehicle.getId().toString()));
        } catch (WriterException | IOException e) {
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
        Vehicule vehicule = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Véhicule non trouvé"));
        vehicule.setTypeVehicule(newType);
        return vehicleRepository.save(vehicule);
    }

    @Override
    public Vehicule updateStatutVehicule(Long id, StatutVehicule newStatut) {
        Vehicule vehicule = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Véhicule non trouvé"));
        vehicule.setStatutVehicule(newStatut);
        return vehicleRepository.save(vehicule);
    }

    @Override
    public Vehicule updateVehicleFeatures(Long id, List<Long> featureIds) {
        Vehicule vehicule = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Véhicule non trouvé"));

        Set<Feature> features = featureService.getFeaturesByIds(featureIds);
        vehicule.setFeatures(features);

        return vehicleRepository.save(vehicule);
    }
}