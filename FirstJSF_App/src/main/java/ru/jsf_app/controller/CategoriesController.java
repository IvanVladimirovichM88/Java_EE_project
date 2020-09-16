package ru.jsf_app.controller;


import ru.jsf_app.persist.CategoriesRepository;
import ru.jsf_app.persist.Category;
import ru.jsf_app.persist.Product;
import ru.jsf_app.persist.ProductRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class CategoriesController implements Serializable {

    @Inject
    private ProductRepository productRepository;

    @Inject
    private CategoriesRepository categoriesRepository;

    private Category category;

    public List<Category> getAllCategories(){
        return categoriesRepository.findAll();
    }

    public String createCategory(){
        this.category = new Category();
        return "/newCategory.xhtml?faces-redirect=true";
    }

    public String saveCategory(){
        categoriesRepository.insert(category);

        return "/category.xhtml?faces-redirect=true";
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
