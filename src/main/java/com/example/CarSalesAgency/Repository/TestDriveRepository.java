package com.example.CarSalesAgency.Repository;

import com.example.CarSalesAgency.Entities.TestDrive;
import com.example.CarSalesAgency.enums.TestDriveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TestDriveRepository extends JpaRepository<TestDrive, Long> {

    List<TestDrive> findByUser_Id(Long userId); // Trouver les test drives par utilisateur
    List<TestDrive> findByVehicule_Id(Long vehiculeId);   // Trouver les test drives par voiture
    List<TestDrive> findByStatus(TestDriveStatus status); // Trouver les test drives par statut
}
