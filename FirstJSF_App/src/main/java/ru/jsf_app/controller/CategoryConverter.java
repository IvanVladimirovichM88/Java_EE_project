package ru.jsf_app.controller;

import ru.jsf_app.persist.CategoriesRepository;
import ru.jsf_app.persist.Category;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class CategoryConverter implements Converter<Category> {

    @Inject
    private CategoriesRepository categoriesRepository;

    @Override
    public Category getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        if (s == null || s.isEmpty()){
            return null;
        }

        return categoriesRepository.findById(Long.parseLong(s))
                .orElseThrow(()->new ConverterException(
                                 new FacesMessage(s+ " - is not correct ID")));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Category category) {
        if (category==null){
            return "";
        }
        return String.valueOf((category.getIdCategory()));
    }
}
