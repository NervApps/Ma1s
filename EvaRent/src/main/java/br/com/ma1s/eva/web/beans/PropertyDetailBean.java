/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.web.beans;

import br.com.ma1s.eva.model.Property;
import br.com.ma1s.eva.service.PropertyService;
import br.com.ma1s.eva.web.beans.common.ManagedBean;
import br.com.ma1s.eva.web.util.Message;
import br.com.ma1s.eva.web.util.MessageType;
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
    private static final String PAGE_LOCK = "property_lock?faces-redirect-true";
    private static final String PAGE_SEARCH = "property_search?faces-redirect-true";
    
    @Getter private String profileImg;
    @Getter private Property property;
    @Getter private List<String> images;
    
    @Inject private SearchPropertyBean searchBean;
    @Inject private PropertyService service;
    
    @PostConstruct
    public void init() {
        property = getParam(PARAM_NAME, Property.class);
        profileImg = null;
        loadImages();
    }
    
    public void loadImages() {
        images = new ArrayList<>();
        
        if (property != null) {
            final File folder = new File(getPath() + IMG_PATH + property.getId());
            if (folder.isDirectory()) {
               for (File file : folder.listFiles()) {
                   if (profileImg == null)
                       profileImg = file.getName();
                   
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
        
        final Message msg = new Message(MessageType.INFO, "Imóvel atualizado com sucesso");
        msg.show();
        return PAGE_SEARCH;
    }
    
    public String delete() {
        service.remove(property);
        
        final Message msg = new Message(MessageType.INFO, "Imóvel removido com sucesso");
        msg.show();
        
        searchBean.search();
        return PAGE_SEARCH;
    }
}
