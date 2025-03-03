package com.example.CarSalesAgency.Controller;


import com.example.CarSalesAgency.Entities.User;
import com.example.CarSalesAgency.Services.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserInterface userInterface;

    @PostMapping("adduser")
    public User addUser(@RequestBody User user) {
        return userInterface.addUser(user);
    }

    @PatchMapping("updateUser/{id}")
    public User updateUser(@PathVariable Long id , @RequestBody User user) {
        return userInterface.UpdateUser(id,user);
    }

    @DeleteMapping("deleteUser/{id}")
    public void deleteUser(@PathVariable Long id) {
        userInterface.DeleteUser(id);
    }

}
