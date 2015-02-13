/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.web.beans;

import br.com.ma1s.eva.model.Property;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Getter;

/**
 *
 * @author Vitor
 */
@Named @ViewScoped
public class PropertyBean implements Serializable {
    @Getter private Property property;
    
    @PostConstruct
    public void init() {
        property = new Property();
    }
}
