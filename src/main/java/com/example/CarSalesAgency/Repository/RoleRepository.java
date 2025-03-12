package com.example.CarSalesAgency.Repository;

import com.example.CarSalesAgency.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
