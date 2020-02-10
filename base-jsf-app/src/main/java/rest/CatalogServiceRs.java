package rest;

import store.Catalog;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/products")
public interface CatalogServiceRs {

    @PUT
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(Catalog product);

    @POST
    @Path("/update")
    void update(Catalog product);

    @DELETE
    @Path("/{id}/id")
    void delete(@PathParam("id") long id);

    @GET
    @Path("/{id}/id")
    @Produces(MediaType.APPLICATION_JSON)
    Catalog findById(@PathParam("id") long id);

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    List<Catalog> findAll();
}
