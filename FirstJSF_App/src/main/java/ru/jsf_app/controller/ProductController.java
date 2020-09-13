package ru.jsf_app.controller;


import ru.jsf_app.persist.CategoriesRepository;
import ru.jsf_app.persist.Category;
import ru.jsf_app.persist.Product;
import ru.jsf_app.persist.ProductRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@SessionScoped
@Named
public class ProductController implements Serializable {

    @Inject
    private ProductRepository productRepository;

    @Inject
    private CategoriesRepository categoriesRepository;

    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getAllProducts()  {
        return productRepository.findAll();
    }

    public String editProduct(Product product) {
        this.product = product;
        return "/product.xhtml?faces-redirect=true";
    }

    public void deleteProduct(Product product) {
        productRepository.delete(product.getId());
    }

    public String createProduct() {
        this.product = new Product();
        return "/product.xhtml?faces-redirect=true";
    }

    public String saveProduct() {
        if (product.getId() != null) {
            this.getProduct().getCategory().getIdCategory();
            productRepository.update(product);
        } else {
            productRepository.insert(product);
        }
        return "/index.xhtml?faces-redirect=true";
    }

    public List<Category> getAllCategories(){
        return categoriesRepository.findAll();
    }

//    public void addInBasket(Product product) throws SQLException {
//        productRepository.addInBasket(product.getId());
//    }


}

