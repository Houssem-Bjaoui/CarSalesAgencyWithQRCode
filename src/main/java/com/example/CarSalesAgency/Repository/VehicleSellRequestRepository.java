package com.example.CarSalesAgency.Repository;

import com.example.CarSalesAgency.Entities.vehicleSellRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleSellRequestRepository extends JpaRepository<vehicleSellRequest, Long> {
}

