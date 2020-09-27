package ru.jsf_app.controller.homePF;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.jsf_app.service.ProductDAO;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;



@Named
@FacesConverter(value = "converterCategoryProductDAO", managed = true)
public class CategoryConverterProductDAO implements Converter {

    private static final Logger logger = LoggerFactory.getLogger(CategoryConverterProductDAO.class);

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        logger.info("start --> getAsObject  CategoryConverterProductDAO ");
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        logger.info("start --> getAsString  CategoryConverterProductDAO ");
        if (o != null){
            return ((ProductDAO)o).getNameCategory();
        }else {
            return null;
        }
    }
}


//@Named
//@FacesConverter(value = "themeConverter", managed = true)
//public class ThemeConverter implements Converter {
//
//    @Inject private ThemeService themeService;
//
//    @Override
//    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
//        if(value != null && value.trim().length() > 0) {
//            try {
//                return themeService.getThemes().get(Integer.parseInt(value));
//            } catch(NumberFormatException e) {
//                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
//            }
//        }
//        else {
//            return null;
//        }
//    }
//
//    @Override
//    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
//        if(object != null) {
//            return String.valueOf(((Theme) object).getId());
//        }
//        else {
//            return null;
//        }
//    }
//}