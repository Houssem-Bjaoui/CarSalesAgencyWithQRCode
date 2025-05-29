package com.example.CarSalesAgency.ServiceImplement;

import com.example.CarSalesAgency.Entities.vehicleSellRequest;
import com.example.CarSalesAgency.Repository.VehicleSellRequestRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VehicleSellRequestService {

    private final VehicleSellRequestRepository repository;

    private final EmailService emailService;


    public VehicleSellRequestService(VehicleSellRequestRepository repository,EmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }

    public vehicleSellRequest createRequest(vehicleSellRequest request) {
        request.setSubmittedAt(LocalDateTime.now());
        request.setStatus("EN_ATTENTE");
        return repository.save(request);
    }

    public List<vehicleSellRequest> getAllRequests() {
        return repository.findAll();
    }


    public vehicleSellRequest acceptRequest(Long requestId) {
        vehicleSellRequest request = repository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Demande non trouvée"));

        request.setStatus("ACCEPTE");
        vehicleSellRequest saved = repository.save(request);

        // Envoi de l’e-mail
        String subject = "Votre demande de vente a été acceptée";
        String body = String.format("""
        Bonjour %s,

        Votre demande de vente du véhicule %s %s (%d) a été acceptée par notre agence.
        Nous vous contacterons très prochainement pour finaliser les démarches.

        Merci de votre confiance,
        L’équipe de l’agence.
    """, request.getFullName(), request.getBrand(), request.getModel(), request.getYear());

        emailService.sendEmail(request.getEmail(), subject, body);

        return saved;
    }


    @Transactional
    public void deleteRequest(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Demande introuvable avec l'ID: " + id);
        }
        repository.deleteById(id);
    }

}
