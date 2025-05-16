package com.example.CarSalesAgency.ServiceImplement;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeycloakAdminService {

    private final Keycloak keycloak;
    private final String realm = "master"; // Remplacez par le nom de votre realm

    public KeycloakAdminService() {
        this.keycloak = KeycloakBuilder.builder()
                .serverUrl("http://localhost:8086") // URL de votre serveur Keycloak
                .realm("master") // Utilisez master ou le realm d'administration
                .grantType(OAuth2Constants.PASSWORD)
                .clientId("admin-cli")
                .username("admin") // Votre user admin
                .password("admin") // Votre mot de passe admin
                .build();
    }

    public void updateUserRole(String userId, String newRoleName) {
        List<RoleRepresentation> realmRoles = keycloak.realm(realm).roles().list();
        RoleRepresentation newRole = realmRoles.stream()
                .filter(r -> r.getName().equals(newRoleName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Role non trouvé"));

        // Supprimer les anciens rôles
        List<RoleRepresentation> currentRoles = keycloak.realm(realm).users().get(userId).roles().realmLevel().listAll();
        keycloak.realm(realm).users().get(userId).roles().realmLevel().remove(currentRoles);

        // Ajouter le nouveau rôle
        keycloak.realm(realm).users().get(userId).roles().realmLevel().add(List.of(newRole));
    }
}

