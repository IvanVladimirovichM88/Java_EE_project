//package ru.jsf_app.controller;
//
//import ru.jsf_app.persist.BasketProductJsf;
//import ru.jsf_app.persist.BasketRepositoryJsf;
//import ru.jsf_app.persist.ProductRepository;
//
//import javax.enterprise.context.SessionScoped;
//import javax.inject.Inject;
//import javax.inject.Named;
//import java.io.Serializable;
//import java.sql.SQLException;
//import java.util.List;
//
//@SessionScoped
//@Named
//public class BasketControllerJsf  implements Serializable {
//
//    @Inject
//    private BasketRepositoryJsf basketRepositoryJsf;
//    @Inject
//    private ProductRepository productRepository;
//
//    private BasketProductJsf basketProduct;
//
//    public BasketProductJsf getBasketProduct() {
//        return basketProduct;
//    }
//
//    public void setBasketProduct(BasketProductJsf basketProduct) {
//        this.basketProduct = basketProduct;
//    }
//
//    public List<BasketProductJsf> getAllBasketProduct(){
//        return basketRepositoryJsf.findAll();
//    }
//    public String deleteProduct(BasketProductJsf basketProduct) {
//        basketRepositoryJsf.delete(basketProduct.getIdBasket());
//        return "/basket.xhtml?faces-redirect=true";
//    }
//
//    public String addProduct(BasketProductJsf basketProduct) {
//        int count = basketProduct.getCount();
//        basketProduct.setCount( ++count );
//        basketRepositoryJsf.update(basketProduct);
//        return "/basket.xhtml?faces-redirect=true";
//    }
//
//    public String removeProduct(BasketProductJsf basketProduct) {
//        int count = basketProduct.getCount();
//        if(count > 1) {
//            basketProduct.setCount( --count );
//            basketRepositoryJsf.update(basketProduct);
//        }else {
//            basketRepositoryJsf.delete(basketProduct.getIdBasket());
//        }
//
//        return "/basket.xhtml?faces-redirect=true";
//    }
//
//}
