package ru.jsf_app.rest;


import ru.jsf_app.service.category.CategoryDAO;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/category")
public interface CategoryServiceRs {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<CategoryDAO> findAll();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(CategoryDAO categoryDAO);
}
