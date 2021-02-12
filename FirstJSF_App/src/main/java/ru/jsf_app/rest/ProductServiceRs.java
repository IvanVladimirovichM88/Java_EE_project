package ru.jsf_app.rest;

import ru.jsf_app.service.ProductDAO;
import ru.jsf_app.service.category.CategoryDAO;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/product")
public interface ProductServiceRs {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(ProductDAO productDAO);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(ProductDAO productDAO);

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void delete(@PathParam("id") long id);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductDAO> findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    ProductDAO findByIdRs(@PathParam("id")long id);

    @GET
    @Path("/finByName/{productName}")
    @Produces(MediaType.APPLICATION_JSON)
    ProductDAO findByName(@PathParam("productName") String productName);

    @GET
    @Path("/findAllProductsByCategoryName/{categoryName}")
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductDAO> findByCategoryNameRs(@PathParam("categoryName") String categoryName );

    @GET
    @Path("/findAllProductByCategoryId/{categoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductDAO> findByCategoryId(@PathParam("categoryId") Long categoryId );

    @PUT
    @Path("/changeProductCategory")
    @Consumes(MediaType.APPLICATION_JSON)
    void changeProductCategory(ProductDAO productDAO, CategoryDAO categoryDAO);



}
