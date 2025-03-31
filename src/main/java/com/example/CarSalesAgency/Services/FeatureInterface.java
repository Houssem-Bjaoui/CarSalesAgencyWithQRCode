package com.example.CarSalesAgency.Services;

import com.example.CarSalesAgency.Entities.Feature;
import java.util.List;
import java.util.Set;

public interface FeatureInterface {
    Feature addFeature(Feature feature);
    Feature updateFeature(Long id, Feature feature);
    List<Feature> getAllFeatures();
    Feature getFeatureById(Long id);
    void deleteFeature(Long id);
    Set<Feature> getFeaturesByIds(List<Long> ids);
}