package com.example.CarSalesAgency.ServiceImplement;

import com.example.CarSalesAgency.DTO.UserUpdateDTO;
import com.example.CarSalesAgency.Entities.PasswordResetToken;
import com.example.CarSalesAgency.Entities.User;
import com.example.CarSalesAgency.Repository.PasswordResetTokenRepository;
import com.example.CarSalesAgency.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private KeycloakUserService keycloakUserService;


    public void createPasswordResetToken(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("Aucun utilisateur trouvé avec cet email.");
        }

        // Supprimer anciens tokens
        tokenRepository.deleteByUserId(user.getId());

        // Générer un nouveau token
        String token = UUID.randomUUID().toString();
        LocalDateTime expiration = LocalDateTime.now().plusHours(1);

        PasswordResetToken resetToken = new PasswordResetToken(token, user.getId(), expiration);
        tokenRepository.save(resetToken);

        // Construire le lien de réinitialisation
        String resetLink = "http://localhost:4200/reset-password?token=" + token; // remplace l'URL par ton frontend

        // Préparer le corps de l'email
        String subject = "Réinitialisation de votre mot de passe";
        String message = "<p>Bonjour " + user.getFirstName() + ",</p>"
                + "<p>Vous avez demandé à réinitialiser votre mot de passe. "
                + "Cliquez sur le lien ci-dessous pour le faire :</p>"
                + "<p><a href=\"" + resetLink + "\">Réinitialiser mon mot de passe</a></p>"
                + "<p>Ce lien expirera dans 1 heure.</p>";

        // Envoyer l'email
        emailService.sendEmail(user.getEmail(), subject, message);
    }

    public void resetPassword(String token, String newPassword) {
        Optional<PasswordResetToken> optionalToken = tokenRepository.findByToken(token);
        if (optionalToken.isEmpty()) {
            throw new IllegalArgumentException("Token invalide.");
        }

        PasswordResetToken resetToken = optionalToken.get();

        if (resetToken.getExpirationDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Le token a expiré.");
        }

        Optional<User> optionalUser = userRepository.findById(resetToken.getUserId());
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("Utilisateur non trouvé.");
        }
        User user = optionalUser.get();

        UserUpdateDTO dto = new UserUpdateDTO();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPassword(newPassword);

        keycloakUserService.updateKeycloakUserProfile(user.getId(), dto);

        tokenRepository.delete(resetToken);
    }
}