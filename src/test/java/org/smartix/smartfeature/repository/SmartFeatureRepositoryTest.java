package org.smartix.smartfeature.repository;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.smartix.smartfeature.entity.SmartFeature;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class SmartFeatureRepositoryTest {

    @Inject
    SmartFeatureRepository smartFeatureRepository;

    @Test
    void findBySearchNotFound() {
        String defaultQuery = "SELECT s FROM SmartFeature s WHERE s.featureIdentifier = 'LIGHT' AND s.featureName = 'Lighting Control'";
        List<SmartFeature> smartFeatures =  smartFeatureRepository.findBySearch(defaultQuery);
        assertNotNull(smartFeatures);
        assertTrue(smartFeatures.isEmpty());
    }


}