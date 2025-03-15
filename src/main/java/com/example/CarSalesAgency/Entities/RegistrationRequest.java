package com.example.CarSalesAgency.Entities;

import lombok.Data;

@Data
public class RegistrationRequest {

    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
}
