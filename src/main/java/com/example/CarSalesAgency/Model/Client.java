package com.example.CarSalesAgency.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data

    public class Client extends User {

    @Column(nullable = false , unique = true)
    private String phoneNumber;

    }

