package ru.jsf_app.persist;

import org.apache.taglibs.standard.tag.common.fmt.RequestEncodingSupport;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Named
public class CategoriesRepository {

    private static final Logger logger = LoggerFactory.getLogger(CategoriesRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager entityManager;
    @Inject
    private UserTransaction userTransaction;

    public CategoriesRepository(){}

    @PostConstruct
    public void init()  {
        logger.info("category repository init() start !!!");
        if (this.findAll().isEmpty()){
            logger.info("No category in category repositories!");

            try {
                userTransaction.begin();

                this.insert(new Category("NoteBook"));
                this.insert(new Category("NetBook"));
                this.insert(new Category("Tablet"));

                userTransaction.commit();
            } catch (Exception e) {
                try {
                    userTransaction.rollback();
                    e.printStackTrace();
                } catch (SystemException systemException) {
                    systemException.printStackTrace();
                }
            }
        }
    }

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
}
