package com.example.CarSalesAgency.ServiceImplement;

import com.example.CarSalesAgency.Entities.Role;
import com.example.CarSalesAgency.Repository.RoleRepository;
import com.example.CarSalesAgency.Services.RoleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements RoleInterface {

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public Role add(Role role) {
        return roleRepository.save(role);
    }
}
