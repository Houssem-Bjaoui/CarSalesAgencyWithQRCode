package com.example.CarSalesAgency.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(unique=true)
    @NotNull
    private String email;
    @NotNull
    private String password;

    @ManyToMany
    @JoinTable(name = "userRole" , joinColumns = @JoinColumn(name = "id"),
    inverseJoinColumns = @JoinColumn(name = "idrole"))
    private Set<Role> roles=new HashSet<>();

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    private List<Vehicule> vehicules;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)

    private List<Comment> comments;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "LikeId",referencedColumnName = "id")
    private Like like;


}
