package com.example.CarSalesAgency.Controller;

import com.example.CarSalesAgency.Entities.TestDrive;
import com.example.CarSalesAgency.Services.TestDriveInterface;
import com.example.CarSalesAgency.enums.TestDriveStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("testDrive")
public class TestDriveController {

@Autowired
    private TestDriveInterface testDriveInterface;

    @PostMapping
    public ResponseEntity<TestDrive> createTestDrive(@RequestBody TestDrive testDrive) {
        TestDrive savedTestDrive = testDriveInterface.createTestDrive(testDrive);
        return new ResponseEntity<>(savedTestDrive, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestDrive> updateTestDrive(@PathVariable Long id, @RequestBody TestDrive testDriveDetails) {
        TestDrive updatedTestDrive = testDriveInterface.updateTestDrive(id, testDriveDetails);
        return ResponseEntity.ok(updatedTestDrive);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTestDrive(@PathVariable Long id) {
        testDriveInterface.deleteTestDrive(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<TestDrive>> getAllTestDrives() {
        List<TestDrive> testDrives = testDriveInterface.getAllTestDrives();
        return ResponseEntity.ok(testDrives);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TestDrive>> getTestDrivesByUser(@PathVariable Long userId) {
        List<TestDrive> testDrives = testDriveInterface.getTestDrivesByUser(userId);
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
