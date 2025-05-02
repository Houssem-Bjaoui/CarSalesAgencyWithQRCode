package com.example.CarSalesAgency.Controller;

import com.example.CarSalesAgency.ServiceImplement.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/send-email")
    public ResponseEntity<String> sendEmail() {
        try {
            String to = "houssembjaoui7050@gmail.com";
            String subject = "Test Email";
            String body = "This is a test email sent from Spring Boot!";

            emailService.sendEmail(to, subject, body);
            return ResponseEntity.ok("Email sent successfully!");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Failed to send email: " + e.getMessage());
        }
    }
}