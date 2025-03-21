package com.example.CarSalesAgency.Entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.util.List;
;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    private  String id ;
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;




    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Comment> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Like> likes;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private  List<TestDrive> testDrives;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Favoris> favorises ;
}

