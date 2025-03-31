
package com.example.CarSalesAgency.Repository;

import com.example.CarSalesAgency.Entities.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {
    Set<Feature> findByIdIn(List<Long> ids);
    boolean existsByName(String name);
}
