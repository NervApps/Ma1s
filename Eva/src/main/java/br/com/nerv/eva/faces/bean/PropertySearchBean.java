/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nerv.eva.faces.bean;

import br.com.nerv.eva.faces.stereotype.ViewBean;
import br.com.nerv.eva.faces.util.Messages;
import br.com.nerv.eva.model.Property;
import br.com.nerv.eva.service.PropertyService;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;

/**
 * Managed bean que controla a tela de busca de imóveis.
 * @author vitor
 */
@ViewBean
public class PropertySearchBean implements Serializable {
    @Getter @Setter private Property property;
    @Getter private List<Property> properties;
    @Inject private PropertyService service;
    @Inject private ImageStreamBean streamer;
    @Inject private Messages msg;
    
    @PostConstruct
    public void init() {
        property = new Property();
        properties = new ArrayList();
    }
    
    public void search() {
        properties.clear();
        properties.addAll(service.findByAttributes(property));
    }
    
    public String edit() {
        putPropertyParam();
        return "cad_property?faces-redirect=true";
    }
    
    public String detail() {
        putPropertyParam();
        return "details?faces-redirect=true";
    }
    
    public void remove() throws IOException {
        service.remove(property);
        streamer.removeImages(property);
        properties.remove(property);
        msg.info("Imóvel removido com sucesso");
    }
    
    private void putPropertyParam() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Flash flash = ctx.getExternalContext().getFlash();
        flash.put("property", property);
    }
}