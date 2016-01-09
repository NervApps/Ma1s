/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nerv.eva.faces.bean;

import br.com.nerv.eva.faces.stereotype.ViewBean;
import br.com.nerv.eva.model.Account;
import br.com.nerv.eva.model.Property;
import br.com.nerv.eva.model.Proprietor;
import br.com.nerv.eva.model.enums.PropertyType;
import br.com.nerv.eva.service.PropertyService;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import lombok.Getter;

/**
 * Managed bean que controla as telas de cadastro de imóveis
 * @author vitor
 */
@ViewBean
public class PropertyBean implements Serializable {
    @Getter private Property property;
    @Inject private PropertyService service;
    
    @PostConstruct
    public void init() {
        property = getFromContext();
        if (property == null) {
            final Proprietor p = new Proprietor();
            p.setAccount(new Account());

            property = new Property();
            property.setProprietor(p);
        }
    }
    
    public void save() {
        service.newProperty(property);
    }
    
    public List<PropertyType> getTypes() {
        return Arrays.asList(PropertyType.values());
    }
    
    private Property getFromContext() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Flash flash = ctx.getExternalContext().getFlash();
        Object get = flash.get("property");
        return get == null ? null : (Property) get;
    }
}
