package com.example.CarSalesAgency.DTO;

public class TestDriveDecisionRequest {
    private Long testDriveId;
    private String decision; // "ACCEPTED" ou "REJECTED"
    private String message;  // Message personnalisé pour l’email

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