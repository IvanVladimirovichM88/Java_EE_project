package ru.jsf_app.service.category;

import ru.jsf_app.persist.Category;
import ru.jsf_app.service.ProductDAO;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class CategoryDAO implements Serializable {

    private Long idCategory;
    private String fldCategory;
    private List<ProductDAO> productDAOList = new ArrayList<>();

    public CategoryDAO(){}

    public CategoryDAO(Category category){
        this.idCategory = category.getIdCategory();
        this.fldCategory = category.getFldCategory();
        if(!category.getProducts().isEmpty()){
            productDAOList = category.getProducts().stream().map(ProductDAO::new).collect(Collectors.toList());
        }
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public String getFldCategory() {
        return fldCategory;
    }

    public List<ProductDAO> getProductDAOList() {
        return productDAOList;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public void setFldCategory(String fldCategory) {
        this.fldCategory = fldCategory;
    }

    public void setProductDAOList(List<ProductDAO> productDAOList) {
        this.productDAOList = productDAOList;
    }
}


/*
<p:outputLabel value="Itemtip:" for="itemTip" />
<p:autoComplete id="itemTip" value="#{autoCompleteView.theme3}" completeMethod="#{autoCompleteView.completeTheme}"
        var="theme" itemLabel="#{theme.displayName}" itemValue="#{theme}" converter="#{themeConverter}">
<f:facet name="itemtip">
<h:panelGrid columns="2" cellpadding="5">
<f:facet name="header">
<h:graphicImage name="showcase/images/themeswitcher/themeswitcher-#{theme.name}.png" alt="#{theme.name}" styleClass="ui-theme" />
</f:facet>

<h:outputText value="Display:" />
<h:outputText value="#{theme.displayName}" />

<h:outputText value="Key" />
<h:outputText value="#{theme.name}" />
</h:panelGrid>
</f:facet>
</p:autoComplete>



<p:outputLabel for="@next" value="Advanced:" />
<p:selectOneMenu id="advanced" value="#{selectOneMenuView.theme}" converter="#{themeConverter}"
        panelStyle="width:180px" effect="fade" var="t" style="width:160px" filter="true" filterMatchMode="startsWith">
<f:selectItems value="#{selectOneMenuView.themes}" var="theme" itemLabel="#{theme.displayName}" itemValue="#{theme}" />

<p:column style="width:10%">
<h:graphicImage name="showcase/images/themeswitcher/themeswitcher-#{t.name}.png" alt="#{t.name}" styleClass="ui-theme" />
</p:column>

<p:column>
<f:facet name="header">
<h:outputText value="Name"/>
</f:facet>
<h:outputText value="#{t.displayName}" />
</p:column>

<f:facet name="footer">
<p:separator />
<h:outputText value="#{selectOneMenuView.themes.size()} themes" style="font-weight:bold;"/>
</f:facet>
</p:selectOneMenu>*/

