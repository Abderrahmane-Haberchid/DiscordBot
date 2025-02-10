package api;

import models.Role;
import services.RoleService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/roles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoleResource {

    private final RoleService roleService;

    public RoleResource(RoleService roleService){
        this.roleService = roleService;
    }

    @GET
    @Path("/all")
    public Response getAllRoles() {
        return Response.ok(roleService.getRoles()).build();
    }

    @GET
    @Path("/role/{id}")
    public Response getRoleById(@PathParam("id") Long id) {
        Role role = roleService.getRole(id);
        if (role != null) {
            return Response.ok(role).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/add")
    public Response createRole(Role role) {
        roleService.addRole(role);
        return Response.status(Response.Status.CREATED).entity(role).build();
    }

    @PUT
    @Path("/update/")
    public Response updateRole(Role updatedRole) {
        roleService.updateRole(updatedRole);
        return Response.status(Response.Status.OK).build();
    }


    @DELETE
    @Path("/delete/{id}")
    public Response deleteRole(@PathParam("id") Long id) {
            roleService.deleteRole(id);
            return Response.noContent().build();

    }
}

