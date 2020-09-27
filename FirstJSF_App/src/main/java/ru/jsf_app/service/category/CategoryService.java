package ru.jsf_app.service.category;

import ru.jsf_app.service.ProductDAO;

import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.Optional;

@Local
public interface CategoryService {
    @TransactionAttribute
    public void insert(CategoryDAO categoryDAO);
    @TransactionAttribute
    public void update(CategoryDAO categoryDAO);
    @TransactionAttribute
    public void delete(Long id);

    public Optional<CategoryDAO> findById(Long id);

    public List<CategoryDAO> findAll();

    public List<ProductDAO> getAllProductCategory(Long id);
}
