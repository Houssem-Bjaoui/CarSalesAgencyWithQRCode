package com.example.CarSalesAgency.ServiceImplement;

import com.example.CarSalesAgency.Entities.Favoris;
import com.example.CarSalesAgency.Entities.User;
import com.example.CarSalesAgency.Entities.Vehicule;
import com.example.CarSalesAgency.Repository.FavorisRepository;
import com.example.CarSalesAgency.Repository.UserRepository;
import com.example.CarSalesAgency.Repository.VehiculeRepository;
import com.example.CarSalesAgency.Services.FavorisInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavorisService implements FavorisInterface {

    @Autowired
    private FavorisRepository favorisRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehiculeRepository vehiculeRepository;

    @Override
    @Transactional
    public Favoris addToFavoris(String userId, Long vehiculeId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        Vehicule vehicule = vehiculeRepository.findById(vehiculeId)
                .orElseThrow(() -> new RuntimeException("Véhicule introuvable"));

        if (favorisRepository.existsByUser_IdAndVehicule_Id(userId, vehiculeId)) {
            throw new RuntimeException("Ce véhicule est déjà en favori");
        }

        Favoris favoris = new Favoris(user, vehicule);
        return favorisRepository.save(favoris);
    }

    @Override
    @Transactional
    public void removeFromFavoris(String userId, Long vehiculeId) {
        favorisRepository.deleteByUser_IdAndVehicule_Id(userId, vehiculeId);
    }

    @Override
    public List<Favoris> getFavorisByUser(String userId) {
        return favorisRepository.findByUser_Id(userId);
    }
}