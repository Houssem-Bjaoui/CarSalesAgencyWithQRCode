package com.example.CarSalesAgency.Repository;

import com.example.CarSalesAgency.DTO.VehiculeSearchCriteria;
import com.example.CarSalesAgency.Entities.Vehicule;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VehiculeRepositoryImpl implements VehiculeRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Vehicule> searchByCriteria(VehiculeSearchCriteria criteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Vehicule> query = cb.createQuery(Vehicule.class);
        Root<Vehicule> root = query.from(Vehicule.class);

        List<Predicate> predicates = new ArrayList<>();

        // Filtre sur la marque
        if (criteria.getMarque() != null) {
            predicates.add(cb.equal(root.get("marque"), criteria.getMarque()));
        }

        // Filtre sur le modèle
        if (criteria.getModele() != null) {
            predicates.add(cb.equal(root.get("modele"), criteria.getModele()));
        }

        // Filtre sur le prix (plage)
        if (criteria.getPrixMin() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("prix"), criteria.getPrixMin()));
        }
        if (criteria.getPrixMax() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("prix"), criteria.getPrixMax()));
        }

        // Filtre sur le kilométrage (plage)
        if (criteria.getKilometrageMin() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("kilometrage"), criteria.getKilometrageMin()));
        }
        if (criteria.getKilometrageMax() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("kilometrage"), criteria.getKilometrageMax()));
        }

        // Filtre sur l'année de fabrication (plage)
        if (criteria.getAnneeMin() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("anneeFabrication"), criteria.getAnneeMin()));
        }
        if (criteria.getAnneeMax() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("anneeFabrication"), criteria.getAnneeMax()));
        }

        // Filtre sur l'énergie
        if (criteria.getEnergie() != null) {
            predicates.add(cb.equal(root.get("energie"), criteria.getEnergie()));
        }

        // Filtre sur la boîte de vitesse
        if (criteria.getBoiteVitesse() != null) {
            predicates.add(cb.equal(root.get("boiteVitesse"), criteria.getBoiteVitesse()));
        }

        // Filtre sur la mise en circulation (plage)
        if (criteria.getMiseEnCirculationMin() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("miseEnCirculation"), criteria.getMiseEnCirculationMin()));
        }
        if (criteria.getMiseEnCirculationMax() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("miseEnCirculation"), criteria.getMiseEnCirculationMax()));
        }

// Filtre sur la puissance fiscale (plage)
        if (criteria.getPuissanceFiscaleMin() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("puissanceFiscale"), criteria.getPuissanceFiscaleMin()));
        }
        if (criteria.getPuissanceFiscaleMax() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("puissanceFiscale"), criteria.getPuissanceFiscaleMax()));
        }

// Filtre sur la carrosserie
        if (criteria.getCarrosserie() != null) {
            predicates.add(cb.equal(root.get("carrosserie"), criteria.getCarrosserie()));
        }

        // Appliquer les filtres à la requête
        query.where(cb.and(predicates.toArray(new Predicate[0])));

        return entityManager.createQuery(query).getResultList();
    }

}