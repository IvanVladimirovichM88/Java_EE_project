package ru.jsf_app.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;
import java.util.Optional;

@WebService
public interface ProductServiceWs {

    @WebMethod
    List<ProductDAO> findAll();

    @WebMethod
    void insert(ProductDAO productDAO);

    @WebMethod
    void delete(long id);

    @WebMethod
    Optional<ProductDAO> findById(long id);

    @WebMethod
    ProductDAO  findByName(String productName);

    @WebMethod
    List<ProductDAO> findByCategoryId(Long categoryId);



}
