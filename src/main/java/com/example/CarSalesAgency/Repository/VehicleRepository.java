package com.example.CarSalesAgency.Repository;
import com.example.CarSalesAgency.Model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Long>{
}
