package com.example.CarSalesAgency.ServiceImplement;

import com.example.CarSalesAgency.Entities.User;
import com.example.CarSalesAgency.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class KeycloakUserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Récupère ou crée un utilisateur basé sur les informations du token JWT Keycloak
     */
    @Transactional
    public User getCurrentUser(Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String keycloakId = jwt.getSubject();

        Optional<User> existingUser = userRepository.findById(keycloakId);

        if (existingUser.isPresent()) {
            return existingUser.get();
        } else {
            // Créer un nouvel utilisateur si n'existe pas
            User newUser = new User();
            newUser.setId(keycloakId);
            newUser.setUsername(jwt.getClaim("preferred_username"));
            newUser.setEmail(jwt.getClaim("email"));
            newUser.setFirstName(jwt.getClaim("given_name"));
            newUser.setLastName(jwt.getClaim("family_name"));

            return userRepository.save(newUser);
        }
    }
}