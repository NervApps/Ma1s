/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.web.beans;

import br.com.ma1s.eva.model.Property;
import br.com.ma1s.eva.web.beans.common.ManagedBean;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
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
    
    @Getter private String profileImg;
    @Getter private Property property;
    @Getter private List<String> images;
    
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
}
