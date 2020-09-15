package ru.jsf_app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.jsf_app.persist.CategoriesRepository;
import ru.jsf_app.persist.Category;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

//@SessionScoped
//@Named
//@FacesConverter(value = "CategoryConverter", forClass = Category.class)
//public class CategoryConverter implements Converter<Category>, Serializable {
//
//    private static final Logger logger = LoggerFactory.getLogger(CategoriesRepository.class);
//
//    @Inject
//    private CategoriesRepository categoriesRepository;
//
//    public CategoryConverter(){
//        logger.info("start category converter!");
//    }
//
//    @Override
//    public Category getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
//
//        return s;
////        if (s == null || s.isEmpty()){
////            return null;
////        }
////        Category category = categoriesRepository.findById(Long.parseLong(s)).get();
////
////        return categoriesRepository.findById(Long.parseLong(s))
////                .orElseThrow(()->new ConverterException(
////                                 new FacesMessage(s+ " - is not correct ID")));
//    }
//
//    @Override
//    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Category category) {
//        if (category==null){
//            return "";
//        }
//        return String.valueOf((category.getIdCategory()));
//    }
//}
