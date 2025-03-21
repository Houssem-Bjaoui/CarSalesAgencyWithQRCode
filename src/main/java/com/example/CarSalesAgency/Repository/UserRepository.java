package com.example.CarSalesAgency.Repository;

import com.example.CarSalesAgency.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // Méthodes de recherche supplémentaires si nécessaires
    User findByEmail(String email);
    User findByUsername(String username);
}