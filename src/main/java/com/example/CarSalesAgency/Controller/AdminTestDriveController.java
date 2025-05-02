package com.example.CarSalesAgency.Controller;

import com.example.CarSalesAgency.DTO.TestDriveDecisionRequest;
import com.example.CarSalesAgency.ServiceImplement.TestDriveDecisionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/admin/test-drive")
public class AdminTestDriveController {

    private final TestDriveDecisionService decisionService;

    public AdminTestDriveController(TestDriveDecisionService decisionService) {
        this.decisionService = decisionService;
    }

    @PostMapping("/decision")
    public ResponseEntity<?> takeDecision(@Valid @RequestBody TestDriveDecisionRequest request) {
        try {
            decisionService.applyDecision(request);
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Décision appliquée avec succès"
            ));
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(Map.of("error", e.getReason()));
        }
    }
}

