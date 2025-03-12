package com.example.CarSalesAgency.ServiceImplement;

import com.example.CarSalesAgency.Entities.Role;
import com.example.CarSalesAgency.Entities.User;
import com.example.CarSalesAgency.Repository.RoleRepository;
import com.example.CarSalesAgency.Repository.UserRepository;
import com.example.CarSalesAgency.Services.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService implements UserInterface {

    @Autowired
    private UserRepository userRepository;

   @Autowired
   private RoleRepository roleRepository;


    @Override
    public User addUser(User user) {
        Role defaulRole= roleRepository.findById(1L).orElse(null);

        user.setRole(defaulRole);
        return  userRepository.save(user);
    }
    @Override
    public User UpdateUser(Long id, User user) {
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());
        user.setEmail(user.getEmail());
        return userRepository.save(user);
    }



    @Override
    public void DeleteUser(Long id) {
        userRepository.deleteById(id);
    }


}
