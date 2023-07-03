package org.smartix.smartfeature.repository;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.smartix.smartfeature.entity.SmartFeature;
import org.smartix.smartfeature.entity.SmartFeature;

import java.util.List;

@ApplicationScoped
public class SmartFeatureRepository implements PanacheRepository<SmartFeature> {
    public List<SmartFeature> findBySearch(String formedQuery) {
        return list(formedQuery);
    }
}
