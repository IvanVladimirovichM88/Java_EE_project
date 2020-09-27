package ru.jsf_app.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Stateless
public class ProductRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager entityManager;

    public ProductRepository() {
    }

    @TransactionAttribute
    public void insert(Product product) {
        entityManager.persist(product);
    }

    @TransactionAttribute
    public void update(Product product) {
        entityManager.merge(product);
    }

    @TransactionAttribute
    public void delete(long id) {
        Product product = entityManager.find(Product.class, id);
        if (product != null){
            entityManager.remove(product);
        }
    }

    public Optional<Product> findById(long id) {
        Product product = entityManager.find(Product.class, id);
        if (product != null){
            return Optional.of(product);
        }
        return Optional.empty();
    }

    public List<Product> findAll() {
        return entityManager.createQuery("from Product",Product.class).getResultList();
    }


}

