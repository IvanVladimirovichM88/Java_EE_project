package ru.jsf_app.persist;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "basket")
public class BasketProductJsf implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBasket;
    @Column
    private int count;

    @ManyToOne
    private Product product;

    public BasketProductJsf(){
    }

    public BasketProductJsf(Product product){
        this.product = product;
        this.count = 0;
    }

    public Long getIdBasket() {
        return idBasket;
    }

    public int getCount() {
        return count;
    }

    public Product getProduct() {
        return product;
    }

    public void setIdBasket(Long idBasket) {
        this.idBasket = idBasket;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
