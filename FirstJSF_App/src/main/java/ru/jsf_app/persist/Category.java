package ru.jsf_app.persist;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;
    @Column (length = 128, nullable = false)
    private String fldCategory;
    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Product> products = new ArrayList<>();

    public Category(){}

    public Category( String fldCategory ){
        this.fldCategory = fldCategory;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public String getFldCategory() {
        return fldCategory;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public void setFldCategory(String fldCategory) {
        this.fldCategory = fldCategory;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
