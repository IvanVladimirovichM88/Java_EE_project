package ru.jsf_app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.jsf_app.persist.CategoriesRepository;
import ru.jsf_app.persist.Category;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@SessionScoped
@Named
public class CategoriesController implements Serializable {
    @Inject
    private CategoriesRepository categoriesRepository;

    private static final Logger logger = LoggerFactory.getLogger(CategoriesRepository.class);

    public List<Category> getAllCategories(){
        try {
            return categoriesRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("\n --> Error categoriesRepository.findAll()");
            return null;
        }
    }


}
