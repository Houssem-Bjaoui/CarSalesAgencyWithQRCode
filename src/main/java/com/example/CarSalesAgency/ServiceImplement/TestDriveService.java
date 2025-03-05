package com.example.CarSalesAgency.ServiceImplement;
import com.example.CarSalesAgency.Entities.TestDrive;
import com.example.CarSalesAgency.Entities.User;
import com.example.CarSalesAgency.Entities.Vehicule;
import com.example.CarSalesAgency.Repository.TestDriveRepository;
import com.example.CarSalesAgency.Repository.UserRepository;
import com.example.CarSalesAgency.Repository.VehicleRepository;
import com.example.CarSalesAgency.Services.TestDriveInterface;
import com.example.CarSalesAgency.enums.TestDriveStatus;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestDriveService implements TestDriveInterface {
    @Autowired
    private VehicleRepository vehiculeRepository;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestDriveRepository testDriveRepository;

    @Override
    @Transactional
    public TestDrive createTestDrive(TestDrive testDrive) {
        // Validation de l'existence de l'utilisateur
        User user = userRepository.findById(testDrive.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        // Validation de l'existence de la voiture
        Vehicule vehicule = vehiculeRepository.findById(testDrive.getVehicule().getId())
                .orElseThrow(() -> new RuntimeException("Car not found"));

        testDrive.setUser(user);
        testDrive.setVehicule(vehicule);
        return testDriveRepository.save(testDrive);
    }

    @Override
    @Transactional
    public TestDrive updateTestDrive(Long id, TestDrive testDriveDetails) {
        TestDrive testDrive = testDriveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TestDrive not found"));

        // Mettre à jour le status seulement si celui-ci est présent dans la requête
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
    public List<TestDrive> getTestDrivesByUser(Long userId) {
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
}

