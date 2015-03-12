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
    private static final String IMG_PATH = "/resources/property-img/";
    private static final String PARAM_NAME = "property";
    private static final String PAGE_LOCK = "lock_property_customer?faces-redirect-true";
    private static final String PAGE_SEARCH = "property_search?faces-redirect-true";
    
    @Getter private Property property;
    @Getter private List<String> images;
    
    @Inject private SearchPropertyBean searchBean;
    @Inject private PropertyService service;
    
    @PostConstruct
    public void init() {
        property = getParam(PARAM_NAME, Property.class);
        if (property != null) {
            property = service.getUpdated(property.getId());
            loadImages();
        } else
            toPage(PAGE_SEARCH, true);
    }
    
    public void loadImages() {
        images = new ArrayList<>();
        
        if (property != null) {
            final File folder = new File(getPath() + IMG_PATH + property.getId());
            if (folder.isDirectory()) {
               for (File file : folder.listFiles()) {
                   images.add(file.getName());
               }
            }
        }
    }
    
    public String lock() {
        putParam(PARAM_NAME, property);
        return PAGE_LOCK;
    }
    
    public String unlock() {
        searchBean.search();
        info("Imóvel atualizado com sucesso");
        return PAGE_SEARCH;
    }
    
    public String delete() {
        service.remove(property);
        info("Imóvel excluído com sucesso");
        searchBean.search();
        return PAGE_SEARCH;
    }
}
