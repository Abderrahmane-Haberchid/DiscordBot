package api;

import models.Groupe;
import services.GroupeService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/groupes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GroupeResource {

    private final GroupeService groupeService;

    @Inject
    public GroupeResource(GroupeService groupeService) {
        this.groupeService = groupeService;
    }


    @GET
    @Path("/all")
    public Response getAllGroupes() {
        return Response.ok(groupeService.getAllGroupes()).build();
    }

    @GET
    @Path("/groupe/{id}")
    public Response getGroupeById(@PathParam("id") Long id) {
        Groupe groupe = groupeService.getGroupe(id);
        if (groupe != null) {
            return Response.ok(groupe).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/add")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createGroupe(Groupe groupe) {
        groupeService.addGroupe(groupe);
        return Response.status(Response.Status.CREATED).entity(groupe).build();
    }

    @PUT
    @Path("/update/")
    public Response updateGroupe(Groupe updatedGroupe) {
        groupeService.updateGroupe(updatedGroupe);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteGroupe(@PathParam("id") Long id) {
        if (groupeService.deleteGroupe(id))
            return Response.noContent().build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }
}
