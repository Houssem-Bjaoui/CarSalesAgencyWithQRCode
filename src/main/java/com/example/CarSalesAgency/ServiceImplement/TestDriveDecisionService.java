package com.example.CarSalesAgency.ServiceImplement;

import com.example.CarSalesAgency.DTO.TestDriveDecisionRequest;
import com.example.CarSalesAgency.Entities.TestDrive;
import com.example.CarSalesAgency.Repository.TestDriveRepository;
import com.example.CarSalesAgency.enums.TestDriveStatus;
import org.springframework.stereotype.Service;

@Service
public class TestDriveDecisionService {

    private final TestDriveRepository testDriveRepository;
    private final EmailService emailService; // Un service que tu dois avoir ou créer

    public TestDriveDecisionService(TestDriveRepository testDriveRepository, EmailService emailService) {
        this.testDriveRepository = testDriveRepository;
        this.emailService = emailService;
    }

    public void applyDecision(TestDriveDecisionRequest request) {
        TestDrive testDrive = testDriveRepository.findById(request.getTestDriveId())
                .orElseThrow(() -> new RuntimeException("Test drive non trouvé"));

        TestDriveStatus newStatus = TestDriveStatus.valueOf(request.getDecision());

        testDrive.setStatus(newStatus);
        testDriveRepository.save(testDrive);

        // Envoi de l'email au client
        String subject = "Mise à jour de votre demande de test drive";
        String body = request.getMessage();
        String to = testDrive.getUser().getEmail(); // Assure-toi que User a bien une adresse email

        emailService.sendEmail(to, subject, body);
    }
}

