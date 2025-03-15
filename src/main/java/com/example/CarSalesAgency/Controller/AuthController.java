package com.example.CarSalesAgency.Controller;

import com.example.CarSalesAgency.Entities.LoginRequest;
import com.example.CarSalesAgency.Entities.RefreshTokenRequest;
import com.example.CarSalesAgency.Entities.RegistrationRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RestTemplate restTemplate;
    private final String keycloakBaseUrl;
    private final String keycloakRealm;
    private final String clientId;
    private final String clientSecret;

    @Autowired
    public AuthController(
            RestTemplate restTemplate,
            @Value("${spring.security.oauth2.client.provider.keycloak.issuer-uri}") String issuerUri,
            @Value("${spring.security.oauth2.client.registration.demo.client-id}") String clientId,
            @Value("${spring.security.oauth2.client.registration.demo.client-secret}") String clientSecret) {
        this.restTemplate = restTemplate;
        // Extract base URL and realm from issuer-uri
        // Example: http://localhost:8086/realms/master
        this.keycloakBaseUrl = issuerUri.substring(0, issuerUri.indexOf("/realms/"));
        this.keycloakRealm = issuerUri.substring(issuerUri.lastIndexOf("/") + 1);
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody LoginRequest loginRequest) {
        String tokenEndpoint = keycloakBaseUrl + "/realms/" + keycloakRealm + "/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "password");
        formData.add("client_id", clientId);
        formData.add("client_secret", clientSecret);
        formData.add("username", loginRequest.getUsername());
        formData.add("password", loginRequest.getPassword());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(tokenEndpoint, request, Map.class);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid credentials", "message", e.getMessage()));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody RegistrationRequest registrationRequest) {
        String userEndpoint = keycloakBaseUrl + "/admin/realms/" + keycloakRealm + "/users";

        // First, we need to get an admin token
        String adminToken = getAdminToken();
        if (adminToken == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to get admin token"));
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(adminToken);

        Map<String, Object> userRepresentation = Map.of(
                "username", registrationRequest.getUsername(),
                "email", registrationRequest.getEmail(),
                "enabled", true,
                "firstName", registrationRequest.getFirstName(),
                "lastName", registrationRequest.getLastName(),
                "credentials", new Object[]{
                        Map.of(
                                "type", "password",
                                "value", registrationRequest.getPassword(),
                                "temporary", false
                        )
                }
        );

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(userRepresentation, headers);

        try {
            restTemplate.postForEntity(userEndpoint, request, Void.class);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("message", "User created successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Failed to create user", "message", e.getMessage()));
        }
    }

    private String getAdminToken() {
        String tokenEndpoint = keycloakBaseUrl + "/realms/" + keycloakRealm + "/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "client_credentials");
        formData.add("client_id", clientId);
        formData.add("client_secret", clientSecret);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(tokenEndpoint, request, Map.class);
            return (String) response.getBody().get("access_token");
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(@RequestParam String refreshToken) {
        String logoutEndpoint = keycloakBaseUrl + "/realms/" + keycloakRealm + "/protocol/openid-connect/logout";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", clientId);
        formData.add("client_secret", clientSecret);
        formData.add("refresh_token", refreshToken);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, headers);

        try {
            restTemplate.postForEntity(logoutEndpoint, request, Void.class);
            return ResponseEntity.ok(Map.of("message", "Logout successful"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Logout failed", "message", e.getMessage()));
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        String tokenEndpoint = keycloakBaseUrl + "/realms/" + keycloakRealm + "/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "refresh_token");
        formData.add("client_id", clientId);
        formData.add("client_secret", clientSecret);
        formData.add("refresh_token", refreshTokenRequest.getRefreshToken());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(tokenEndpoint, request, Map.class);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid refresh token", "message", e.getMessage()));
        }
    }


    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        String userEndpoint = keycloakBaseUrl + "/admin/realms/" + keycloakRealm + "/users";

        // Obtenir un token admin
        String adminToken = getAdminToken();
        if (adminToken == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to get admin token"));
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(adminToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        try {
            ResponseEntity<Object[]> response = restTemplate.exchange(
                    userEndpoint,
                    HttpMethod.GET,
                    request,
                    Object[].class
            );
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Failed to fetch users", "message", e.getMessage()));
        }
    }

}