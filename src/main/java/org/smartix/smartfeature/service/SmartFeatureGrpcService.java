package org.smartix.smartfeature.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.smartix.*;
import org.smartix.smartfeature.entity.SmartFeature;
import org.smartix.smartfeature.repository.SmartFeatureRepository;
import org.smartix.smartfeature.models.Responses;
import org.smartix.smartfeature.models.SearchCriteria;
import org.smartix.smartfeature.repository.SmartFeatureRepository;

import java.util.List;

@GrpcService
public class SmartFeatureGrpcService implements SmartFeatureGrpc {

    @Inject
    SmartFeatureRepository smartFeatureRepository;


    @Override
    public Uni<GetSmartFeaturesResponse> getAllSmartFeatures(Empty request) {
        List<org.smartix.smartfeature.entity.SmartFeature> smartFeatures = smartFeatureRepository.listAll();
        return (Uni<GetSmartFeaturesResponse>) smartFeatures;
    }

    @Override
    public Uni<SmartFeatureResponse> createSmartFeature(SmartFeatureRequest request) {
        Responses responses = new Responses();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        org.smartix.smartfeature.models.SmartFeatureRequest smartFeatureRequestReq = objectMapper.convertValue(request, org.smartix.smartfeature.models.SmartFeatureRequest.class);
        org.smartix.smartfeature.entity.SmartFeature smartFeature = new SmartFeatureService().buildEntity(smartFeatureRequestReq);
        smartFeatureRepository.persist(smartFeature);
        if (smartFeatureRepository.isPersistent(smartFeature)){
            return Uni.createFrom()
                    .item("")
                    .map(
                            msg -> SmartFeatureResponse.newBuilder()
                                    .setResponseCode(Response.Status.CREATED.getStatusCode())
                                    .setResponseMessage("Feature Created Successfully!!")
                                    .build()
                    );
        }
        return Uni.createFrom()
                .item("")
                .map(
                        msg -> SmartFeatureResponse.newBuilder()
                                .setResponseCode(Response.Status.BAD_REQUEST.getStatusCode())
                                .setResponseMessage(Response.Status.BAD_REQUEST.getReasonPhrase())
                                .build()
                );
    }

    @Override
    public Uni<GetSmartFeaturesResponse> searchSmartFeature(SearchSmartFeatureRequest request) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        SearchCriteria searchCriteria = objectMapper.convertValue(request, SearchCriteria.class);
        String formedQuery = new SmartFeatureService().buildQuery(searchCriteria);;
        List<org.smartix.smartfeature.entity.SmartFeature> smartFeatures = smartFeatureRepository
                .findBySearch(formedQuery);
        return (Uni<GetSmartFeaturesResponse>) smartFeatures;
    }

    @Override
    public Uni<SmartFeatureResponse> deleteSmartFeatureById(SmartFeatureIDRequest request) {
        smartFeatureRepository.deleteById(request.getFeatureId());
        return Uni.createFrom()
                .item("")
                .map(
                        msg -> SmartFeatureResponse.newBuilder()
                                .setResponseCode(0)
                                .setResponseMessage("Delete Successful!")
                                .build()
                );
    }
}




