package ru.jsf_app.controller;

import ru.jsf_app.persist.BasketProductJsf;
import ru.jsf_app.persist.BasketRepositoryJsf;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@SessionScoped
@Named
public class BasketControllerJsf  implements Serializable {

    @Inject
    private BasketRepositoryJsf basketRepositoryJsf;

    private BasketProductJsf basketProduct;

    public BasketProductJsf getBasketProduct() {
        return basketProduct;
    }

    public void setBasketProduct(BasketProductJsf basketProduct) {
        this.basketProduct = basketProduct;
    }

    public List<BasketProductJsf> getAllBasketProduct(){

        try {
            return basketRepositoryJsf.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public String deleteProduct(BasketProductJsf basketProduct) throws SQLException {
        basketRepositoryJsf.delete(basketProduct.getIdProductInBasket());
        return "/basket.xhtml?faces-redirect=true";
    }

    public String addProduct(BasketProductJsf basketProduct) throws SQLException {
        basketRepositoryJsf.add(basketProduct.getIdProductInBasket());
        return "/basket.xhtml?faces-redirect=true";
    }

    public String removeProduct(BasketProductJsf basketProduct) throws SQLException {
        basketRepositoryJsf.remove(basketProduct.getIdProductInBasket());
        return "/basket.xhtml?faces-redirect=true";
    }

}
