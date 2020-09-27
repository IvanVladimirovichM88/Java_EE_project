package ru.jsf_app.controller.cart;


import ru.jsf_app.service.ProductDAO;
import ru.jsf_app.service.cart.CartProduct;
import ru.jsf_app.service.cart.CartService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


@Named
@SessionScoped
public class CartController implements Serializable {

    @EJB
    private CartService cartService;

    public List<CartProduct> getAllProducts(){

        return cartService.getAllProducts();
    }

    public void addToCart(ProductDAO productDAO){
        cartService.addProduct(productDAO);
    }

    public String addProduct(CartProduct cartProduct){
        cartService.addProduct(cartProduct.getProductDAO());
        return "/basketPF.xhtml?faces-redirect=true";
    }

    public String removeProduct(CartProduct cartProduct){
        cartService.removeProduct(cartProduct.getProductDAO());
        return "/basketPF.xhtml?faces-redirect=true";
    }
}
