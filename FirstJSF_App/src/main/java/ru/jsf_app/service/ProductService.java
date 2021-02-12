package ru.jsf_app.service;

import ru.jsf_app.persist.Product;

import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.Optional;

@Local
public interface ProductService {

    @TransactionAttribute
    void insert(ProductDAO productDAO);

    @TransactionAttribute
    void update(ProductDAO productDAO);

    @TransactionAttribute
    void delete(long id) ;

    Optional<ProductDAO> findById(long id) ;

    List<ProductDAO> findAll() ;

}
