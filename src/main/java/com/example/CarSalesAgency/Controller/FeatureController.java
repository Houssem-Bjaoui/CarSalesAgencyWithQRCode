package com.example.CarSalesAgency.Controller;

import com.example.CarSalesAgency.Entities.Feature;
import com.example.CarSalesAgency.Services.FeatureInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/features") // Changed path to /api/features
public class FeatureController {

    @Autowired
    private FeatureInterface featureService;

    @PostMapping
    public ResponseEntity<Feature> createFeature(@RequestBody Feature feature) {
        return ResponseEntity.ok(featureService.addFeature(feature));
    }

    @GetMapping
    public ResponseEntity<List<Feature>> getAllFeatures() {
        return ResponseEntity.ok(featureService.getAllFeatures());
    }
}