package com.example.CarSalesAgency.ServiceImplement;

import com.example.CarSalesAgency.DTO.TestDriveDecisionRequest;
import com.example.CarSalesAgency.Entities.TestDrive;
import com.example.CarSalesAgency.Repository.TestDriveRepository;
import com.example.CarSalesAgency.enums.TestDriveStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;



@Service
public class TestDriveDecisionService {

    private final TestDriveRepository testDriveRepository;
    private final EmailService emailService;

    private static final Logger logger = LoggerFactory.getLogger(TestDriveDecisionService.class);

    // URL de ton logo
    private static final String LOGO_URL = "https://i.postimg.cc/pTmPKFq3/logo1.jpg";
    // à remplacer

    @Autowired
    public TestDriveDecisionService(
            TestDriveRepository testDriveRepository,
            EmailService emailService

    ) {
        this.testDriveRepository = testDriveRepository;
        this.emailService = emailService;
    }

    public void applyDecision(TestDriveDecisionRequest request) {
        try {
            TestDrive testDrive = findTestDriveById(request.getTestDriveId());
            TestDriveStatus newStatus = applyTestDriveDecision(testDrive, request);
            sendDecisionEmail(testDrive, request, newStatus);
        } catch (RuntimeException e) {
            logger.error("Test drive non trouvé avec ID: " + request.getTestDriveId());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Test drive non trouvé avec ID: " + request.getTestDriveId()
            );
        }
    }

    private TestDrive findTestDriveById(Long testDriveId) {
        return testDriveRepository.findById(testDriveId)
                .orElseThrow(() -> new RuntimeException("Test drive non trouvé"));
    }

    private TestDriveStatus applyTestDriveDecision(TestDrive testDrive, TestDriveDecisionRequest request) {
        TestDriveStatus newStatus = TestDriveStatus.valueOf(request.getDecision());
        testDrive.setStatus(newStatus);
        testDrive.setDecisionDate(LocalDateTime.now());
        testDriveRepository.save(testDrive);
        logger.info("Décision '{}' appliquée sur le test drive ID={} par l'admin.", request.getDecision(), testDrive.getId());
        return newStatus;
    }

    private void sendDecisionEmail(TestDrive testDrive, TestDriveDecisionRequest request, TestDriveStatus newStatus) {
        String subject = "Mise à jour de votre demande de test drive";
        String body = generateHtmlBody(request, newStatus);
        String to = testDrive.getUser().getEmail(); // Assure-toi que User a bien une adresse email

        emailService.sendEmail(to, subject, body);
        logger.info("Email envoyé à {} concernant la décision de test drive.", to);
    }

    private String generateHtmlBody(TestDriveDecisionRequest request, TestDriveStatus newStatus) {
        return "<div style='font-family: Arial, sans-serif;'>"
                + "<img src='" + LOGO_URL + "' alt='Logo CarSalesAgency' style='height: 80px;'/><br><br>"
                + "<h2>Bonjour,</h2>"
                + "<p>Votre demande de test drive a été <strong style='color:" +
                (newStatus == TestDriveStatus.ACCEPTED ? "green" : "red") + "'>" +
                newStatus.name().toLowerCase() + "</strong>.</p>"
                + "<p>" + request.getMessage() + "</p>"
                + "<br><hr style='margin-top:30px; margin-bottom:10px;'>"
                + "<p style='font-size: 14px; color: #555;'>"
                + "CarSalesAgency<br>"
                + "📞 +216 6 00 00 00 00<br>"
                + "📧 contact@gmail.com<br>"
                + "🌐 <a href='https://CarHub.com'>CarHub.com</a>"
                + "</p>"
                + "</div>";
    }




}


