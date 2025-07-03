package org.uce.controller;

import org.uce.dto.RegisterUserRequest;
import org.uce.entity.User;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @POST
    @Path("/register")
    @Transactional
    public Response register(RegisterUserRequest request) {

        if (User.find("email", request.email).firstResult() != null) {
            return Response.status(Response.Status.CONFLICT)
                           .entity("El correo ya está registrado").build();
        }

        User user = new User();
        user.email = request.email;
        user.username = request.username;
        user.fullName = request.fullName;
        user.phoneNumber = request.phoneNumber;
        user.address = request.address;
        user.passwordHash = request.password;


        try {
            user.role = User.Role.valueOf(request.role.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Rol inválido").build();
        }

        user.persist();

        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    @GET
    @Path("/health")
    public Response healthCheck() {
        return Response.ok("Service is up and running").build();
    }

}
