package com.example.CarSalesAgency.Repository;

import com.example.CarSalesAgency.Entities.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<File,Long> {
    Optional<File> findById(Long id);
    Optional<File> findFileByFilename(String filename);
    boolean existsByFilename(String filename);
}
