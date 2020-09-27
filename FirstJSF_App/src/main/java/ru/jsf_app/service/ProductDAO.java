package ru.jsf_app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.jsf_app.persist.Category;
import ru.jsf_app.persist.Product;


import java.io.Serializable;
import java.math.BigDecimal;

public class ProductDAO implements Serializable {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long idCategory;
    private String nameCategory;

    private static final Logger logger = LoggerFactory.getLogger(ProductDAO.class);

    public ProductDAO(){}

    public ProductDAO(Long id, String name, String description, BigDecimal price, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.idCategory = category.getIdCategory();
        this.nameCategory = category.getFldCategory();
    }

    public ProductDAO (Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();

        if ( product.getCategory() != null ){
            this.idCategory = product.getCategory().getIdCategory();
            this.nameCategory = product.getCategory().getFldCategory();
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        logger.info("ProductDAO().getName() - "+ name);
        return name;
    }

    public String getDescription() {
        logger.info("ProductDAO().getDescription() - "+ description);
        return description;
    }

    public BigDecimal getPrice() {
        logger.info("ProductDAO().getPrice() - "+ price);
        return price;
    }

    public Long getIdCategory() {
        logger.info("ProductDAO().getIdCategory() - "+ idCategory);
        return idCategory;
    }

    public String getNameCategory() {
        logger.info("ProductDAO().getNameCategory() - "+ nameCategory);
        return nameCategory;
    }

    public void setId(Long id) {
        logger.info("ProductDAO().setId(Long id) - "+ id);
        this.id = id;
    }

    public void setName(String name) {
        logger.info("ProductDAO().setName(String name) - "+ name);
        this.name = name;
    }

    public void setDescription(String description) {
        logger.info("ProductDAO().setDescription(String description) - "+ description);
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        logger.info("ProductDAO().setPrice(BigDecimal price) - "+ price);
        this.price = price;
    }

    public void setIdCategory(Long idCategory) {
        logger.info("ProductDAO().setIdCategory(Long idCategory) - "+ idCategory);
        this.idCategory = idCategory;
    }

    public void setNameCategory(String nameCategory) {
        logger.info("ProductDAO().setNameCategory(String nameCategory) - " + nameCategory);
        this.nameCategory = nameCategory;
    }
}
