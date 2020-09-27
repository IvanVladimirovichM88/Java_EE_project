//package ru.jsf_app.controller.categoryPF;
//
//import javax.annotation.PostConstruct;
//import javax.enterprise.context.RequestScoped;
//import javax.inject.Inject;
//import javax.inject.Named;
//import java.util.List;
//
//@Named
//@RequestScoped
//public class SelectManyView {
//
//    private List<String> selectedOptions;
//    private List<String> selectedOptions2;
//    private List<Theme> selectedThemes;
//    private List<Theme> selectedThemes2;
//    private List<Theme> themes;
//
//    @Inject
//    private ThemeService service;
//
//    @PostConstruct
//    public void init() {
//        themes = service.getThemes();
//    }
//
//    public List<Theme> getThemes() {
//        return themes;
//    }
//
//    public void setService(ThemeService service) {
//        this.service = service;
//    }
//
//    public List<String> getSelectedOptions() {
//        return selectedOptions;
//    }
//
//    public void setSelectedOptions(List<String> selectedOptions) {
//        this.selectedOptions = selectedOptions;
//    }
//
//    public List<Theme> getSelectedThemes() {
//        return selectedThemes;
//    }
//
//    public void setSelectedThemes(List<Theme> selectedThemes) {
//        this.selectedThemes = selectedThemes;
//    }
//
//    public List<String> getSelectedOptions2() {
//        return selectedOptions2;
//    }
//
//    public void setSelectedOptions2(List<String> selectedOptions2) {
//        this.selectedOptions2 = selectedOptions2;
//    }
//
//    public List<Theme> getSelectedThemes2() {
//        return selectedThemes2;
//    }
//
//    public void setSelectedThemes2(List<Theme> selectedThemes2) {
//        this.selectedThemes2 = selectedThemes2;
//    }
//}