package ru.jsf_app.service.cart;

import ru.jsf_app.persist.Product;
import ru.jsf_app.service.ProductDAO;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartProduct implements Serializable {

    private ProductDAO productDAO;
    private int count;

    public CartProduct() {
    }

    public CartProduct(ProductDAO productDAO, int count) {
        this.productDAO = productDAO;
        this.count = count;
    }

    public ProductDAO getProductDAO() {
        return productDAO;
    }

    public int getCount() {
        return count;
    }

    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void addCount(){
        this.count++;
    }

    public int cutCount(){
        this.count--;
        return count;
    }

    public BigDecimal getCost(){
        return  (productDAO.getPrice().multiply(BigDecimal.valueOf(count)));
    }
}
