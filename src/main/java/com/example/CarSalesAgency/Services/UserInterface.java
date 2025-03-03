package com.example.CarSalesAgency.Services;

import com.example.CarSalesAgency.Entities.User;

public interface UserInterface {

    User addUser(User user);
    public User UpdateUser(Long id, User user);



    void DeleteUser(Long id);
}
