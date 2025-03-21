package com.example.CarSalesAgency.ServiceImplement;

import com.example.CarSalesAgency.Entities.TestDrive;
import com.example.CarSalesAgency.Entities.User;
import com.example.CarSalesAgency.Entities.Vehicule;
import com.example.CarSalesAgency.Repository.TestDriveRepository;
import com.example.CarSalesAgency.Repository.UserRepository;
import com.example.CarSalesAgency.Repository.VehiculeRepository;
import com.example.CarSalesAgency.Services.TestDriveInterface;
import com.example.CarSalesAgency.enums.TestDriveStatus;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestDriveService implements TestDriveInterface {

    @Autowired
    private VehiculeRepository vehiculeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestDriveRepository testDriveRepository;

    @Override
    @Transactional
    public TestDrive createTestDrive(TestDrive testDrive) {
        // Validation de l'utilisateur et du véhicule
        User user = userRepository.findById(testDrive.getUser().getId())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
        Vehicule vehicule = vehiculeRepository.findById(testDrive.getVehicule().getId())
                .orElseThrow(() -> new RuntimeException("Voiture introuvable"));

        // Définir le statut à PENDING (ignore la valeur envoyée par le client)
        testDrive.setStatus(TestDriveStatus.PENDING);
        testDrive.setUser(user);
        testDrive.setVehicule(vehicule);

        return testDriveRepository.save(testDrive);
    }

    @Override
    @Transactional
    public TestDrive updateTestDrive(Long id, TestDrive testDriveDetails) {
        TestDrive testDrive = testDriveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Test drive non trouvé"));

        // Mettre à jour le statut seulement si celui-ci est présent dans la requête
        if (testDriveDetails.getStatus() != null) {
            testDrive.setStatus(testDriveDetails.getStatus());
        }

        // Si d'autres champs doivent être mis à jour, vérifiez-les de la même manière
        // Note : Ne modifiez pas createdAt pour préserver la date de création

        return testDriveRepository.save(testDrive);
    }

    @Override
    @Transactional
    public void deleteTestDrive(Long id) {
        testDriveRepository.deleteById(id);
    }

    @Override
    public List<TestDrive> getAllTestDrives() {
        return testDriveRepository.findAll();
    }

    @Override
    public List<TestDrive> getTestDrivesByUser(String userId) {
        return testDriveRepository.findByUser_Id(userId);
    }

    @Override
    public List<TestDrive> getTestDrivesByCar(Long carId) {
        return testDriveRepository.findByVehicule_Id(carId);
    }

    @Override
    public List<TestDrive> getTestDrivesByStatus(TestDriveStatus status) {
        return testDriveRepository.findByStatus(status);
    }

    @Override
    public TestDrive getTestDriveById(Long id) {
        return testDriveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Test drive non trouvé"));
    }
}