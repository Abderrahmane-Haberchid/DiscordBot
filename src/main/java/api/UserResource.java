package api;

import models.User;
import services.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    @Inject
    private UserService userService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/test")
    public String test(){
        return "test is working well !!";
    }

    @GET
    @Path("/all")
    public Response getAllUsers() {
        return Response.ok(userService.getUsers()).build();
    }

    @GET
    @Path("/user/{id}")
    public Response getUserById(@PathParam("id") Long id) {
        User user = userService.getUser(id);
        if (user != null) {
            return Response.ok(user).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/add")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {
        userService.createUser(user);
        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    @PUT
    @Path("/update/")
    public Response updateUser(User updatedUser) {
        userService.updateUser(updatedUser);
        return Response.status(Response.Status.OK).build();
        }


    @DELETE
    @Path("/delete/{id}")
    public Response deleteUser(@PathParam("id") Long id) {

        if(userService.deleteUser(id))
            return Response.noContent().build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();

    }
}
