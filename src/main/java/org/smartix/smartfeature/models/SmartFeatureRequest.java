package org.smartix.smartfeature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmartFeatureRequest {
    @Schema(required = true)
    private Long serviceId; // Identifier of the IoT smart service the feature belongs to.
    @Schema(required = true)
    private String featureName; // Name or title of the IoT smart feature.
    @Schema(required = true)
    private String featureType; //Type of IoT Smart Feature
    @Schema(required = true)
    private String featureIdentifier; // Identifier of the IoT smart feature.
    @Schema(required = true)
    private String featureCategory; //Category of IoT Smart Feature
    @Schema(required = true)
    private String featureDescription; // Description of the IoT smart feature and its functionality.
    @Schema(required = true)
    private String featureStatus; // Current status of the IoT smart feature (e.g., enabled, disabled).

}
