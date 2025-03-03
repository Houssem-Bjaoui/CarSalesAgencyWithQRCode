package com.example.CarSalesAgency.Entities;


import com.example.CarSalesAgency.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idrole;

    @Enumerated(EnumType.STRING)
    private UserRole rolename;
@JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<User> users= new HashSet<>();
}