package com.roberteverett.rally.spiral.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.roberteverett.rally.spiral.builder.Spiral;
import com.roberteverett.rally.spiral.builder.SpiralFactory;

@Singleton
@Path("/spiral")
public class SpiralResource {

    private final SpiralFactory spiralFactory;

    @Inject
    public SpiralResource(final SpiralFactory spiralFactory) {
        this.spiralFactory = spiralFactory;
    }

    @GET
    @Path("forward")
    @Produces(MediaType.APPLICATION_JSON)
    public Response forwardSpiralJson() {
        Spiral spiral = spiralFactory.forwardSpiral().build();
        return allowCrossDomainRequests(Response.ok(spiral)).build();
    }

    @GET
    @Path("forward")
    @Produces(MediaType.TEXT_PLAIN)
    public Response forwardSpiralPlainText() {
        Spiral spiral = spiralFactory.forwardSpiral().build();
        return allowCrossDomainRequests(Response.ok(spiral.toString())).build();
    }

    @GET
    @Path("reverse")
    @Produces(MediaType.APPLICATION_JSON)
    public Response reverseSpiralJson() {
        Spiral spiral = spiralFactory.reverseSpiral().build();
        return allowCrossDomainRequests(Response.ok(spiral)).build();
    }

    @GET
    @Path("reverse")
    @Produces(MediaType.TEXT_PLAIN)
    public Response reverseSpiralPlainText() {
        Spiral spiral = spiralFactory.reverseSpiral().build();
        return allowCrossDomainRequests(Response.ok(spiral.toString())).build();
    }

    /**
     * ASSUMPTION We don't have any security concerns for this demo, so we'll allow cross-domain requests.
     */
    ResponseBuilder allowCrossDomainRequests(final ResponseBuilder responseBuilder) {
        return responseBuilder.header("Access-Control-Allow-Origin", "*")
                              .header("Access-Control-Allow-Methods", "GET,OPTIONS,HEAD");
    }

}
