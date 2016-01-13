/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nerv.eva.faces.bean;

import br.com.nerv.eva.faces.stereotype.ViewBean;
import br.com.nerv.eva.model.Property;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import lombok.Getter;

/**
 * Managed bean responsável pelo controle da tela de detalhes do imóvel.
 * @author vitor
 */
@ViewBean
public class PropertyDetailsBean implements Serializable {
    @Getter private Property property;
    @Getter private List<String> images;
    @Inject private ImageStreamBean streamer;
    
    @PostConstruct
    public void init() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Flash flash = ctx.getExternalContext().getFlash();
        property = (Property) flash.get("property");
        images = streamer.loadImages(property);
    }
    
}
