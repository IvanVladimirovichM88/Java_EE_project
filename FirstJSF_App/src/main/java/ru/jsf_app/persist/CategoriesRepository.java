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
public class CategoriesRepository {

    private static final Logger logger = LoggerFactory.getLogger(CategoriesRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager entityManager;

    public CategoriesRepository(){}

    @TransactionAttribute
    public void insert(Category category){
        entityManager.persist(category);
    }

    public List<Category> findAll() {
        return entityManager.createQuery("from Category", Category.class).getResultList();
    }
    public Optional <Category> findById (Long idCategory){
        Category category = entityManager.find(Category.class, idCategory);
        if (category != null){
            return Optional.of(category);
        }
        return Optional.empty();

    }

    public void delete(Long id) {
        Category category = entityManager.find(Category.class, id);
        if ((category!=null) & (category.getProducts().isEmpty())){
            entityManager.remove(category);
        }

    }
}
