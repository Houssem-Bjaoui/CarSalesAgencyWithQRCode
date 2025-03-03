package com.example.CarSalesAgency.Entities;


import com.example.CarSalesAgency.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idrole;
    @Enumerated(EnumType.STRING)
    private UserRole rolename;

}