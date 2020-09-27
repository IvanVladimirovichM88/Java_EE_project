package ru.jsf_app.service;

import ru.jsf_app.persist.Product;

import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.Optional;

@Local
public interface ProductService {

    @TransactionAttribute
    public void insert(ProductDAO productDAO);

    @TransactionAttribute
    public void update(ProductDAO productDAO);

    @TransactionAttribute
    public void delete(long id) ;

    public Optional<ProductDAO> findById(long id) ;

    public List<ProductDAO> findAll() ;

}
