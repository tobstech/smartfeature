package org.smartix.smartfeature.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.smartix.smartfeature.entity.SmartFeature;
import org.smartix.smartfeature.models.Responses;
import org.smartix.smartfeature.models.SmartFeatureRequest;
import org.smartix.smartfeature.repository.SmartFeatureRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@QuarkusTest
class SmartFeatureResourceTest {

    @InjectMock
    SmartFeatureRepository smartFeatureRepository;
    @Inject
    SmartFeatureResource smartFeatureResource;

    private SmartFeature smartFeature;

    @BeforeEach
    void setUp() {
        smartFeature = new SmartFeature();
        smartFeature.setFeatureId(1L);
        smartFeature.setServiceId(1L);
        smartFeature.setFeatureName("Lighting Control");
        smartFeature.setFeatureType("Lighting");
        smartFeature.setFeatureIdentifier("LIGHT");
        smartFeature.setFeatureCategory("Home Automation");
        smartFeature.setFeatureDescription("Control and automate lighting in your home.");
        smartFeature.setFeatureStatus("ENABLED");
    }

    @Test
    void getAllSmartFeatures() {
        List<SmartFeature> smartFeatureList = new ArrayList<>();
        smartFeatureList.add(smartFeature);
        Mockito.when(smartFeatureRepository.listAll()).thenReturn(smartFeatureList);
        Response response = smartFeatureResource.getAllSmartFeatures();
        assertNotNull(response);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertNotNull(response.getEntity());
        List<SmartFeature> entity = (List<SmartFeature>) response.getEntity();
        assertFalse(entity.isEmpty());
        assertEquals(1L, entity.get(0).getFeatureId());
        assertEquals(1L, entity.get(0).getServiceId());
        assertEquals("Lighting Control", entity.get(0).getFeatureName());
        assertEquals("Lighting", entity.get(0).getFeatureType());
        assertEquals("LIGHT", entity.get(0).getFeatureIdentifier());
        assertEquals("Home Automation", entity.get(0).getFeatureCategory());
        assertEquals("Control and automate lighting in your home.", entity.get(0).getFeatureDescription());
        assertEquals("ENABLED", entity.get(0).getFeatureStatus());
    }

    @Test
    void getByServiceId() {
        Mockito.when(smartFeatureRepository.findByIdOptional(1L))
                .thenReturn(Optional.of(smartFeature));
        Response response = smartFeatureResource.getByServiceId(1L);
        assertNotNull(response);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertNotNull(response.getEntity());
        SmartFeature entity = (SmartFeature) response.getEntity();
        assertEquals(1L, entity.getFeatureId());
        assertEquals(1L, entity.getServiceId());
        assertEquals("Lighting Control", entity.getFeatureName());
        assertEquals("Lighting", entity.getFeatureType());
        assertEquals("LIGHT", entity.getFeatureIdentifier());
        assertEquals("Home Automation", entity.getFeatureCategory());
        assertEquals("Control and automate lighting in your home.", entity.getFeatureDescription());
        assertEquals("ENABLED", entity.getFeatureStatus());
    }

    @Test
    void getByServiceIdNotFound() {
        Mockito.when(smartFeatureRepository.findByIdOptional(1L))
                .thenReturn(Optional.empty());
        Response response = smartFeatureResource.getByServiceId(1L);
        assertNotNull(response);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
        assertNull(response.getEntity());
    }

    @Test
    void getByFeatureId() {
        Mockito.when(smartFeatureRepository.findByIdOptional(1L))
                .thenReturn(Optional.of(smartFeature));
        Response response = smartFeatureResource.getByFeatureId(1L);
        assertNotNull(response);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertNotNull(response.getEntity());
        SmartFeature entity = (SmartFeature) response.getEntity();
        assertEquals(1L, entity.getFeatureId());
        assertEquals(1L, entity.getServiceId());
        assertEquals("Lighting Control", entity.getFeatureName());
        assertEquals("Lighting", entity.getFeatureType());
        assertEquals("LIGHT", entity.getFeatureIdentifier());
        assertEquals("Home Automation", entity.getFeatureCategory());
        assertEquals("Control and automate lighting in your home.", entity.getFeatureDescription());
        assertEquals("ENABLED", entity.getFeatureStatus());
    }

    @Test
    void getByFeatureIdNotFound() {
        Mockito.when(smartFeatureRepository.findByIdOptional(1L))
                .thenReturn(Optional.empty());
        Response response = smartFeatureResource.getByFeatureId(1L);
        assertNotNull(response);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
        assertNull(response.getEntity());
    }

    @Test
    void createSmartFeature() {
        Mockito.doNothing().when(smartFeatureRepository).persist(
                ArgumentMatchers.any(SmartFeature.class)
        );

        Mockito.when(smartFeatureRepository.isPersistent(
                ArgumentMatchers.any(SmartFeature.class)
        )).thenReturn(true);

        SmartFeatureRequest smartFeatureRequest = new SmartFeatureRequest();
        smartFeatureRequest.setServiceId(1L);
        smartFeatureRequest.setFeatureName("Temperature Control");
        smartFeatureRequest.setFeatureType("Thermostat");
        smartFeatureRequest.setFeatureIdentifier("TEMP");
        smartFeatureRequest.setFeatureCategory("Climate Control");
        smartFeatureRequest.setFeatureDescription("Manage and control temperature settings.");
        smartFeatureRequest.setFeatureStatus("ENABLED");
        Responses responses = smartFeatureResource.createSmartFeature(smartFeatureRequest);
        assertNotNull(responses);
        assertEquals(Response.Status.CREATED.getStatusCode(),
                responses.getResponseCode());
    }

    @Test
    void createSmartFeatureBadRequest() {
        Mockito.doNothing().when(smartFeatureRepository).persist(
                ArgumentMatchers.any(SmartFeature.class)
        );
        Mockito.when(smartFeatureRepository.isPersistent(
                ArgumentMatchers.any(SmartFeature.class)
        )).thenReturn(false);

        SmartFeatureRequest smartFeatureRequest = new SmartFeatureRequest();
        smartFeatureRequest.setServiceId(1L);
        smartFeatureRequest.setFeatureName("Temperature Control");
        smartFeatureRequest.setFeatureType("Thermostat");
        smartFeatureRequest.setFeatureIdentifier("TEMP");
        smartFeatureRequest.setFeatureCategory("Climate Control");
        smartFeatureRequest.setFeatureDescription("Manage and control temperature settings.");
        smartFeatureRequest.setFeatureStatus("ENABLED");
        Responses responses = smartFeatureResource.createSmartFeature(smartFeatureRequest);
        assertNotNull(responses);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(),
                responses.getResponseCode());
    }

    @Test
    void deleteByFeatureId() {
        Mockito.when(smartFeatureRepository.deleteById(1L))
                .thenReturn(true);
        Responses responses = smartFeatureResource.deleteByFeatureId(1L);
        assertNotNull(responses);
        assertEquals(Response.Status.OK.getStatusCode(), responses.getResponseCode());
    }

    @Test
    void deleteByFeatureIdNotFound() {
        Mockito.when(smartFeatureRepository.deleteById(1L))
                .thenReturn(false);
        Responses responses = smartFeatureResource.deleteByFeatureId(1L);
        assertNotNull(responses);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), responses.getResponseCode());
    }

}