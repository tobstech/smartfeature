package org.smartix.smartfeature.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.smartix.smartfeature.models.SmartFeatureRequest;
import org.smartix.smartfeature.entity.SmartFeature;
import org.smartix.smartfeature.models.SearchCriteria;
import org.smartix.smartfeature.models.SmartFeatureRequest;

public class SmartFeatureService {

    public String buildQuery(SearchCriteria searchCriteria) {

        String defaultQuery = "SELECT s FROM SmartFeature s";

        if(searchCriteria.getFeatureId() != null) {
            if (defaultQuery.contains("WHERE")){
                defaultQuery = defaultQuery + " AND s.featureId = " + searchCriteria.getFeatureId();
            }else{
                defaultQuery = defaultQuery + " WHERE s.featureId = '" + searchCriteria.getFeatureId();
            }
        }

        if(searchCriteria.getServiceId() != null) {
            if (defaultQuery.contains("WHERE")){
                defaultQuery = defaultQuery + " AND s.serviceId = " + searchCriteria.getServiceId();
            }else{
                defaultQuery = defaultQuery + " WHERE s.serviceId = '" + searchCriteria.getServiceId();
            }
        }

        if(searchCriteria.getFeatureName() != null) {
            if (defaultQuery.contains("WHERE")){
                defaultQuery = defaultQuery + " AND s.featureName = '" + searchCriteria.getFeatureName() + "'";
            }else{
                defaultQuery = defaultQuery + " WHERE s.featureName = '" + searchCriteria.getFeatureName() + "'";
            }
        }

        if(searchCriteria.getFeatureType() != null) {
            if (defaultQuery.contains("WHERE")){
                defaultQuery = defaultQuery + " AND s.featureType = '" + searchCriteria.getFeatureType() + "'";
            }else{
                defaultQuery = defaultQuery + " WHERE s.featureType = '" + searchCriteria.getFeatureType() + "'";
            }
        }

        if(searchCriteria.getFeatureIdentifier() != null) {
            if (defaultQuery.contains("WHERE")){
                defaultQuery = defaultQuery + " AND s.featureIdentifier = '" + searchCriteria.getFeatureIdentifier() + "'";
            }else{
                defaultQuery = defaultQuery + " WHERE s.featureIdentifier = '" + searchCriteria.getFeatureIdentifier() + "'";
            }
        }

        if(searchCriteria.getFeatureCategory() != null) {
            if (defaultQuery.contains("WHERE")){
                defaultQuery = defaultQuery + " AND s.featureCategory = '" + searchCriteria.getFeatureCategory() + "'";
            }else{
                defaultQuery = defaultQuery + " WHERE s.featureCategory = '" + searchCriteria.getFeatureCategory() + "'";
            }
        }

        if(searchCriteria.getFeatureStatus() != null) {
            if (defaultQuery.contains("WHERE")){
                defaultQuery = defaultQuery + " AND s.featureStatus = '" + searchCriteria.getFeatureStatus() + "'";
            }else{
                defaultQuery = defaultQuery + " WHERE s.featureStatus = '" + searchCriteria.getFeatureStatus() + "'";
            }
        }

        return defaultQuery;
    }

    public SmartFeature buildEntity(SmartFeatureRequest smartFeatureRequest) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        SmartFeature smartFeature = objectMapper.convertValue(smartFeatureRequest, SmartFeature.class);
        return smartFeature;
    }

}
