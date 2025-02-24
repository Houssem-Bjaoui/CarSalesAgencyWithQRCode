package com.example.CarSalesAgency.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Size(max=10, message =" le prénom ne doit pas depasser 10 caractéres")
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String confPassword;


}
