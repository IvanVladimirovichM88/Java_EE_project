package ru.jsf_app.persist;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 128, nullable = false)
    private String name;
    @Column(length = 1024)
    private String description;
    @Column
    private BigDecimal price;

    @ManyToOne
    private Category category;

//    @OneToMany(
//            mappedBy = "product",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    private List<BasketProductJsf> basketProduct;

    public Product() {
    }

    public Product( String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public String getNameCategory(){
        if(this.category != null){
            return this.category.getFldCategory();
        }else{
            return "No Category";
        }
    }

    public void setCategory(Category category) {
        this.category = category;
    }

//    public List<BasketProductJsf> getBasketProduct() {
//        return basketProduct;
//    }
//
//    public void setBasketProduct(List<BasketProductJsf> basketProduct) {
//        this.basketProduct = basketProduct;
//    }
}
