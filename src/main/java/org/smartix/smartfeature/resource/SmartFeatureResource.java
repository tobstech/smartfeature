package org.smartix.smartfeature.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.smartix.smartfeature.entity.SmartFeature;
import org.smartix.smartfeature.models.Responses;
import org.smartix.smartfeature.models.SearchCriteria;
import org.smartix.smartfeature.models.SmartFeatureRequest;
import org.smartix.smartfeature.repository.SmartFeatureRepository;
import org.smartix.smartfeature.service.SmartFeatureService;

import java.util.List;

@Path("/smart-features")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Smart Feature Resource", description="Smart Feature REST API")
public class SmartFeatureResource {

    @Inject
    SmartFeatureRepository smartFeatureRepository;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            operationId = "getAllSmartFeatures",
            summary = "Get Smart Features",
            description = "Get all smart feature from the database"
    )
    @APIResponse(
            responseCode = "200",
            description = "Returns List of Smart Features.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response getAllSmartFeatures(){
        List<SmartFeature> smartFeatures = smartFeatureRepository.listAll();
        return Response.ok(smartFeatures).build();
    }

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            operationId = "createSmartFeature",
            summary = "Create a Smart Feature",
            description = "Create a smart feature to add inside the database"
    )
    @APIResponse(
            responseCode = "200",
            description = "Smart Feature Creation Completed",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Responses createSmartFeature(
            @RequestBody(
                    description = "Smart Feature to create",
                    required = true,
                    content = @Content(schema = @Schema(implementation = SmartFeatureRequest.class))
            )
            SmartFeatureRequest smartFeatureRequest){
        Responses responses = new Responses();
        SmartFeature smartFeature = new SmartFeatureService().buildEntity(smartFeatureRequest);
        smartFeatureRepository.persist(smartFeature);
        if (smartFeatureRepository.isPersistent(smartFeature)){
            responses.setResponseCode(Response.Status.CREATED.getStatusCode());
            responses.setResponseMessage("Feature Created Successfully!.");
            return responses;
        }
        responses.setResponseCode(Response.Status.BAD_REQUEST.getStatusCode());
        responses.setResponseMessage(Response.Status.BAD_REQUEST.getReasonPhrase());
        return responses;
    }

    @DELETE
    @Path("{featureId}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            operationId = "deleteByFeatureId",
            summary = "Delete an existing smart Feature",
            description = "Delete a smart Feature from the database"
    )
    @APIResponse(
            responseCode = "200",
            description = "Smart Feature Delete",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "Smart Feature not valid",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Responses deleteByFeatureId(@PathParam("featureId") Long featureId){
        Responses responses = new Responses();
        boolean deleted = smartFeatureRepository.deleteById(featureId);
        if (deleted){
            responses.setResponseCode(Response.Status.OK.getStatusCode());
            responses.setResponseMessage("Feature Deleted Successfully!.");
            return responses;
        }
        responses.setResponseCode(Response.Status.NOT_FOUND.getStatusCode());
        responses.setResponseMessage(Response.Status.NOT_FOUND.getReasonPhrase());
        return responses;
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            operationId = "searchSmartFeature",
            summary = "Search for Smart Features",
            description = "Get smart Feature based on search criteria"
    )
    @APIResponse(
            responseCode = "200",
            description = "Feature Search Operation completed",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response searchSmartFeature(SearchCriteria searchCriteria){
        SmartFeatureService smartFeatureService = new SmartFeatureService();
        String formedQuery = smartFeatureService.buildQuery(searchCriteria);
        List<SmartFeature> smartFeatures = smartFeatureRepository
                .findBySearch(formedQuery);
        return Response.ok(smartFeatures).build();
    }




}
