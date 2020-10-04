package ru.jsf_app.rest;

import ru.jsf_app.service.ProductDAO;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/product")
public interface ProductServiceRs {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    void insert(ProductDAO productDAO);

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    void update(ProductDAO productDAO);

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    void delete(@PathParam("id") long id);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductDAO> findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    ProductDAO findByIdRs(@PathParam("id")long id);

}
