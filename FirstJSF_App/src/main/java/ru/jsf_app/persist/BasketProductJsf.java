package ru.jsf_app.persist;

import java.io.Serializable;
import java.math.BigDecimal;

public class BasketProductJsf extends Product implements Serializable {

    private Long idProductInBasket;
    private int count;

    public BasketProductJsf(){
    }

    public BasketProductJsf(Long idProduct, String nameProduct,
                            String descriptionProduct, BigDecimal priceProduct,
                            Long idProductInBasket, int numberOfProduct){

        super( nameProduct, descriptionProduct, priceProduct);
        this.idProductInBasket = idProductInBasket;
        this.count = numberOfProduct;
    }

    public Long getIdProductInBasket() {
        return idProductInBasket;
    }

    public int getCount() {
        return count;
    }

    public void setIdProductInBasket(Long idProductInBasket) {
        this.idProductInBasket = idProductInBasket;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
