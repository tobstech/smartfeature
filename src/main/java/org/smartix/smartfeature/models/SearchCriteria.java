package org.smartix.smartfeature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria {
    private Long featureId;
    private Long serviceId;
    private String featureName;
    private String featureType;
    private String featureIdentifier;
    private String featureCategory;
    private String featureStatus;
}
