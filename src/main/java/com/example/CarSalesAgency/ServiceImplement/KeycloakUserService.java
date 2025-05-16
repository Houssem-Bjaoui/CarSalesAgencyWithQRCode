package com.example.CarSalesAgency.ServiceImplement;

import com.example.CarSalesAgency.DTO.UserUpdateDTO;
import com.example.CarSalesAgency.Entities.User;
import com.example.CarSalesAgency.Repository.UserRepository;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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


    public void updateKeycloakUserProfile(String userId, UserUpdateDTO dto) {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl("http://localhost:8086")
                .realm("master")
                .clientId("admin-cli")
                .username("admin")
                .password("admin")
                .build();

        UserResource userResource = keycloak.realm("master").users().get(userId);

        UserRepresentation user = userResource.toRepresentation();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());

        userResource.update(user);

        // ➕ Mise à jour du mot de passe
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            CredentialRepresentation newPassword = new CredentialRepresentation();
            newPassword.setType(CredentialRepresentation.PASSWORD);
            newPassword.setValue(dto.getPassword());
            newPassword.setTemporary(false); // false = définitif

            userResource.resetPassword(newPassword);
        }
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(String userId) {
        // Suppression dans Keycloak
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl("http://localhost:8086")
                .realm("master")
                .clientId("admin-cli")
                .username("admin")
                .password("admin")
                .build();

        keycloak.realm("master").users().get(userId).remove();

        // Suppression dans la base de données
        userRepository.deleteById(userId);
    }
}