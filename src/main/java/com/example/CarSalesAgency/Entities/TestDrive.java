package com.example.CarSalesAgency.Entities;


import com.example.CarSalesAgency.enums.TestDriveStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="testDrives")
@Getter
@Setter
public class TestDrive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "test_drive_date")
    private LocalDateTime createdAt;


    @Column(name = "decision_date")
    private LocalDateTime decisionDate;


    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TestDriveStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "vehicule_id", referencedColumnName = "id")
    private Vehicule vehicule;
}
