package ru.jsf_app.controller;


import ru.jsf_app.persist.*;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class ProductController implements Serializable {

    @Inject
    private ProductRepository productRepository;

    @Inject
    private CategoriesRepository categoriesRepository;

    @Inject
    private BasketRepositoryJsf basketRepositoryJsf;

    private Product product;

    private Long categoryId;

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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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
            product.setCategory( categoriesRepository.findById(categoryId).get() );
            productRepository.update(product);

        } else {
            product.setCategory( categoriesRepository.findById(categoryId).get() );
            productRepository.insert(product);
        }
        return "/index.xhtml?faces-redirect=true";
    }

    public List<Category> getAllCategories(){
        return categoriesRepository.findAll();
    }

    public void addInBasket(Product product) {
        BasketProductJsf basketProduct = basketRepositoryJsf.findByProduct(product).orElse(new BasketProductJsf(product));
        int count = basketProduct.getCount();
        if(count == 0){
            basketProduct.setCount(1);
            basketRepositoryJsf.insert(basketProduct);
        }else {
            basketProduct.setCount(++count);
            basketRepositoryJsf.update(basketProduct);
        }

    }


}

