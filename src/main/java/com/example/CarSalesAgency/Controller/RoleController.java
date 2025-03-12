package com.example.CarSalesAgency.Controller;


import com.example.CarSalesAgency.Entities.Role;
import com.example.CarSalesAgency.Services.RoleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleInterface roleInterface;

    @PostMapping("/add")
    public Role addRole(@RequestBody Role role)
    {
        return roleInterface.add(role);
    }

}
