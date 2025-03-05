package com.example.CarSalesAgency.Services;

import com.example.CarSalesAgency.Entities.TestDrive;
import com.example.CarSalesAgency.enums.TestDriveStatus;

import java.util.List;

public interface TestDriveInterface {
    // Créer un test drive
    TestDrive createTestDrive(TestDrive testDrive);

    // Mettre à jour un test drive
    TestDrive updateTestDrive(Long id, TestDrive testDriveDetails);

    // Supprimer un test drive
    void deleteTestDrive(Long id);

    // Récupérer tous les test drives
    List<TestDrive> getAllTestDrives();

    // Récupérer les test drives par utilisateur
    List<TestDrive> getTestDrivesByUser(Long userId);

    // Récupérer les test drives par voiture
    List<TestDrive> getTestDrivesByCar(Long carId);

    // Récupérer les test drives par statut
    List<TestDrive> getTestDrivesByStatus(TestDriveStatus status);
}
