package com.example.CarSalesAgency.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TestDriveDecisionRequest {
    @NotNull(message = "L'identifiant du test drive est obligatoire.")
    private Long testDriveId;

    @NotBlank(message = "La décision (ACCEPTED ou REJECTED) est obligatoire.")
    private String decision;

    @NotBlank(message = "Le message ne peut pas être vide.")
    private String message;

    // Getters et setters

    public Long getTestDriveId() {
        return testDriveId;
    }

    public void setTestDriveId(Long testDriveId) {
        this.testDriveId = testDriveId;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}