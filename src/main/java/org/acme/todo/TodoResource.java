package org.acme.todo;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/todos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TodoResource {

    @Inject
    TodoService service;

    @GET
    public List<Todo> listAll() {
        return service.listAll();
    }

    @POST
    public Response create(Todo todo) {
        Todo created = service.create(todo);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @GET
    @Path("/{id}")
    public Todo getById(@PathParam("id") Long id) {
        Todo todo = service.findById(id);
        if (todo == null) {
            throw new NotFoundException("Todo not found");
        }
        return todo;
    }

    @PUT
    @Path("/{id}")
    public Todo update(@PathParam("id") Long id, Todo newData) {
        Todo updated = service.update(id, newData);
        if (updated == null) {
            throw new NotFoundException("Todo not found");
        }
        return updated;
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = service.delete(id);
        if (!deleted) {
            throw new NotFoundException("Todo not found");
        }
        return Response.noContent().build();
    }
}
