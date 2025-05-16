package com.example.CarSalesAgency.DTO;

public class UpdateUserRoleRequest {

    private String userId;
    private String newRole;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNewRole() {
        return newRole;
    }

    public void setNewRole(String newRole) {
        this.newRole = newRole;
    }
}
