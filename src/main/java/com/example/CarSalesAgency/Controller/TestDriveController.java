package com.example.CarSalesAgency.Controller;

import com.example.CarSalesAgency.Entities.TestDrive;
import com.example.CarSalesAgency.Entities.User;
import com.example.CarSalesAgency.ServiceImplement.KeycloakUserService;
import com.example.CarSalesAgency.Services.TestDriveInterface;
import com.example.CarSalesAgency.enums.TestDriveStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("testDrive")
public class TestDriveController {

    @Autowired
    private TestDriveInterface testDriveInterface;

    @Autowired
    private KeycloakUserService keycloakUserService;

    @PostMapping("add")
    public ResponseEntity<TestDrive> createTestDrive(
            Authentication authentication,
            @RequestBody TestDrive testDrive
    ) {
        // Récupérer l'utilisateur authentifié
        User currentUser = keycloakUserService.getCurrentUser(authentication);
        testDrive.setUser(currentUser); // Associer l'utilisateur authentifié au test drive
        TestDrive savedTestDrive = testDriveInterface.createTestDrive(testDrive);
        return new ResponseEntity<>(savedTestDrive, HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<TestDrive> updateTestDrive(
            Authentication authentication,
            @PathVariable Long id,
            @RequestBody TestDrive testDriveDetails
    ) {
        // Récupérer l'utilisateur authentifié
        User currentUser = keycloakUserService.getCurrentUser(authentication);

        // Vérifier que l'utilisateur est bien l'auteur du test drive
        TestDrive existingTestDrive = testDriveInterface.getTestDriveById(id);
        if (!existingTestDrive.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("Action non autorisée : vous n'êtes pas l'auteur de ce test drive");
        }

        TestDrive updatedTestDrive = testDriveInterface.updateTestDrive(id, testDriveDetails);
        return ResponseEntity.ok(updatedTestDrive);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteTestDrive(
            Authentication authentication,
            @PathVariable Long id
    ) {
        // Récupérer l'utilisateur authentifié
        User currentUser = keycloakUserService.getCurrentUser(authentication);

        // Vérifier que l'utilisateur est bien l'auteur du test drive
        TestDrive existingTestDrive = testDriveInterface.getTestDriveById(id);
        if (!existingTestDrive.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("Action non autorisée : vous n'êtes pas l'auteur de ce test drive");
        }

        testDriveInterface.deleteTestDrive(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<TestDrive>> getAllTestDrives() {
        List<TestDrive> testDrives = testDriveInterface.getAllTestDrives();
        return ResponseEntity.ok(testDrives);
    }

    @GetMapping("/me")
    public ResponseEntity<List<TestDrive>> getMyTestDrives(Authentication authentication) {
        // Récupérer l'utilisateur authentifié
        User currentUser = keycloakUserService.getCurrentUser(authentication);
        List<TestDrive> testDrives = testDriveInterface.getTestDrivesByUser(currentUser.getId());
        return ResponseEntity.ok(testDrives);
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<List<TestDrive>> getTestDrivesByCar(@PathVariable Long carId) {
        List<TestDrive> testDrives = testDriveInterface.getTestDrivesByCar(carId);
        return ResponseEntity.ok(testDrives);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TestDrive>> getTestDrivesByStatus(@PathVariable TestDriveStatus status) {
        List<TestDrive> testDrives = testDriveInterface.getTestDrivesByStatus(status);
        return ResponseEntity.ok(testDrives);
    }
}