package ru.jsf_app.controller.homePF;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.jsf_app.persist.*;
import ru.jsf_app.service.ProductDAO;
import ru.jsf_app.service.ProductService;
import ru.jsf_app.service.category.CategoryDAO;
import ru.jsf_app.service.category.CategoryService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class HomePfController implements Serializable {

    @EJB
    private ProductService productService;

    @EJB
    private CategoryService categoryService;

    private ProductDAO productDAO;

    private static final Logger logger = LoggerFactory.getLogger(HomePfController.class);

    public ProductDAO getProductDAO() {
        return productDAO;
    }

    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<ProductDAO> getAllProducts(){
        return productService.findAll();
    }

    public String editProduct(ProductDAO productDAO) {
        this.productDAO = productDAO;
        return "/productPF.xhtml?faces-redirect=true";
    }

    public String saveProduct() {

        if (productDAO.getId() != null) {
            productService.update(productDAO);
        } else {
            productService.insert(productDAO);
        }
        return "/homePF.xhtml?faces-redirect=true";
    }


    public String createProduct() {

        this.productDAO = new ProductDAO();
        return "/productPF.xhtml?faces-redirect=true";
    }

    public List<CategoryDAO> getAllCategories(){

        return this.categoryService.findAll();
    }
}
