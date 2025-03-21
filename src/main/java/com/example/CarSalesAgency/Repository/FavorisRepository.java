package com.example.CarSalesAgency.Repository;

import com.example.CarSalesAgency.Entities.Favoris;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavorisRepository extends JpaRepository<Favoris, Long> {

    boolean existsByUser_IdAndVehicule_Id(String userId, Long vehiculeId);

    Optional<Favoris> findByUser_IdAndVehicule_Id(String userId, Long vehiculeId);

    List<Favoris> findByUser_Id(String userId);

    void deleteByUser_IdAndVehicule_Id(String userId, Long vehiculeId);
}