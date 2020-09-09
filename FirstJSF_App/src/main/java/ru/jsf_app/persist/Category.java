package ru.jsf_app.persist;

import java.io.Serializable;

public class Category implements Serializable {
    private Long idCategory;
    private String fldCategory;

    public Category(){}

    public Category(Long idCategory, String fldCategory){
        this.idCategory=idCategory;
        this.fldCategory = fldCategory;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public String getFldCategory() {
        return fldCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public void setFldCategory(String fldCategory) {
        this.fldCategory = fldCategory;
    }
}
