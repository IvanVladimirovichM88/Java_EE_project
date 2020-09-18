package ru.jsf_app.controller;


import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import ru.jsf_app.persist.*;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class HomePfController implements Serializable {

    @Inject
    private ProductRepository productRepository;

    @Inject
    private CategoriesRepository categoriesRepository;

    @Inject
    private BasketRepositoryJsf basketRepositoryJsf;

    private Product product;
    private Long categoryId;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public String editProduct(Product product) {
        this.product = product;
        return "/product.xhtml?faces-redirect=true";
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

    public void createProduct() {
        this.product = new Product();
    }
}
