package com.example.CarSalesAgency.ServiceImplement;

import com.example.CarSalesAgency.Entities.Feature;
import com.example.CarSalesAgency.Repository.FeatureRepository;
import com.example.CarSalesAgency.Services.FeatureInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class FeatureService implements FeatureInterface {

    @Autowired
    private FeatureRepository featureRepository;

    @Override
    public Feature addFeature(Feature feature) {
        if (featureRepository.existsByName(feature.getName())) {
            throw new RuntimeException("Une feature avec ce nom existe déjà");
        }
        return featureRepository.save(feature);
    }

    @Override
    public Feature updateFeature(Long id, Feature feature) {
        Feature existingFeature = featureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feature non trouvée"));

        if (!existingFeature.getName().equals(feature.getName()) &&
                featureRepository.existsByName(feature.getName())) {
            throw new RuntimeException("Une feature avec ce nom existe déjà");
        }

        existingFeature.setName(feature.getName());

        return featureRepository.save(existingFeature);
    }

    @Override
    public List<Feature> getAllFeatures() {
        return featureRepository.findAll();
    }

    @Override
    public Feature getFeatureById(Long id) {
        return featureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feature non trouvée"));
    }

    @Override
    public void deleteFeature(Long id) {
        featureRepository.deleteById(id);
    }

    @Override
    public Set<Feature> getFeaturesByIds(List<Long> ids) {
        return featureRepository.findByIdIn(ids);
    }
}