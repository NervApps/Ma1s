/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.web.beans;

import br.com.ma1s.eva.model.Property;
import br.com.ma1s.eva.service.PropertyService;
import br.com.ma1s.eva.web.beans.common.ManagedBean;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;

/**
 *
 * @author Vitor
 */
@Named("detailBean") @ViewScoped
public class PropertyDetailBean extends ManagedBean implements Serializable {
    private final String imgPath = "/resources/property-img/";
    private final String propertyParam = "property";
    private final String lockPage = "lock_property_customer?faces-redirect-true";
    private final String searchPage = "property_search?faces-redirect-true";
    private String backPage = "property_search";
    
    @Getter private Property property;
    @Getter private List<String> images;
    
    @Inject private SearchPropertyBean searchBean;
    @Inject private PropertyService service;
    
    @PostConstruct
    public void init() {
        loadProperty();
        loadBackPage();
    }
    
    private void loadBackPage() {
        final String param = getParam(PARAM_FROM_PAGE, String.class);
        if (param != null)
            this.backPage = param;
    }
    
    private void loadProperty() {
        property = getParam(propertyParam, Property.class);
        if (property != null) {
            property = service.getUpdated(property.getId());
            loadImages();
        } else
            toPage(searchPage, true);
    }
    
    public void loadImages() {
        images = new ArrayList<>();
        
        if (property != null) {
            final File folder = new File(getPath() + imgPath + property.getId());
            if (folder.isDirectory()) {
               for (File file : folder.listFiles()) {
                   images.add(file.getName());
               }
            }
        }
    }
    
    public String lock() {
        putParam(propertyParam, property);
        return lockPage;
    }
    
    public String unlock() {
        searchBean.search();
        info("Imóvel atualizado com sucesso");
        return searchPage;
    }
    
    public String delete() {
        service.remove(property);
        info("Imóvel excluído com sucesso");
        searchBean.search();
        return searchPage;
    }
    
    public void back() {
        toPage(backPage, true);
    }
}
