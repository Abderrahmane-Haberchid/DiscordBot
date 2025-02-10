package api;

import models.Canal;
import services.CanalService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/canals")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CanalResource {

    private final CanalService canalService;

    @Inject
    public CanalResource(CanalService canalService) {
        this.canalService = canalService;
    }

    @GET
    @Path("/all")
    public Response getAllCanals() {
        return Response.ok(canalService.getAllCanals()).build();
    }

    @GET
    @Path("/canal/{id}")
    public Response getCanalById(@PathParam("id") Long id) {
        Canal canal = canalService.getCanal(id);
        if (canal != null) {
            return Response.ok(canal).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/add")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCanal(Canal canal) {
        canalService.addCanal(canal);
        return Response.status(Response.Status.CREATED).entity(canal).build();
    }

    @PUT
    @Path("/update/")
    public Response updateCanal(Canal updatedCanal) {
        canalService.updateCanal(updatedCanal);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteCanal(@PathParam("id") Long id) {
        boolean deleteCanal = canalService.deleteCanal(id);
        if (deleteCanal)
            return Response.noContent().build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }
}

