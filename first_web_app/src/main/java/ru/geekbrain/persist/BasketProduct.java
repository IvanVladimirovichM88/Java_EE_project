package ru.geekbrain.persist;

import java.math.BigDecimal;
import java.math.MathContext;

public class BasketProduct extends Product {
    Integer numberOfProduct;
    Integer idInBasket;

    public BasketProduct(Long id, String name, String description, BigDecimal price, Integer numberOfProduct, Integer idInBasket) {

        super(id, name, description, price);
        this.numberOfProduct = numberOfProduct;
        this.idInBasket = idInBasket;
    }

    public Integer getNumberOfProduct() {
        return numberOfProduct;
    }

    public Integer getIdInBasket() {
        return idInBasket;
    }

    public void setNumberOfProduct(Integer numberOfProduct) {
        this.numberOfProduct = numberOfProduct;
    }

    public void setIdInBasket(Integer idInBasket) {
        this.idInBasket = idInBasket;
    }

    public BigDecimal getCost(){
        BigDecimal price = super.getPrice();
        BigDecimal cost = BigDecimal.valueOf(numberOfProduct).multiply( price );

        return price.multiply(cost).setScale(2);}

}
