package org.smartix.smartfeature;

import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@OpenAPIDefinition(
        info = @Info(
                title = "Smart Features APIs",
                description = "Smart Application",
                version = "1.0.0",
                license = @License(
                        name = "MIT",
                        url = "http://localhost:9099"
                )
        ),
        tags = {
                @Tag(name = "smart-features", description = "SmartFeatures")
        }
)
public class SmartFeatureApplication extends Application {
}
