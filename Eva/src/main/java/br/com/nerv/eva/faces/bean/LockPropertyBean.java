/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nerv.eva.faces.bean;

import br.com.nerv.eva.faces.stereotype.ViewBean;
import br.com.nerv.eva.faces.util.Messages;
import br.com.nerv.eva.model.Customer;
import br.com.nerv.eva.model.Property;
import br.com.nerv.eva.model.PropertyCustomer;
import br.com.nerv.eva.service.PropertyCustomerService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import lombok.Getter;

/**
 * Managed bean que controla a tela de reserva de imóvel.
 * @author vitor
 */
@ViewBean
public class LockPropertyBean implements Serializable {
    @Getter private boolean confirmed;
    @Getter private Property property;
    @Getter private PropertyCustomer propertyCustomer;
    @Inject private PropertyCustomerService service;
    @Inject private Messages msg;
    
    @PostConstruct
    public void init() {
        confirmed = false;
        FacesContext ctx = FacesContext.getCurrentInstance();
        Flash flash = ctx.getExternalContext().getFlash();
        property = (Property) flash.get("property");
        
        propertyCustomer = new PropertyCustomer();
        propertyCustomer.setCustomer(new Customer());
        propertyCustomer.setProperty(property);
    }
    
    public void confirm() {
        service.lock(propertyCustomer);
        msg.info("Imóvel reservado com sucesso");
        confirmed = true;
    }
    
    public String getPropertyInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(property.getType().getLabel())
          .append(" no bairro ")
          .append(property.getNeighborhood())
          .append(" situado na ")
          .append(property.getAddress())
          .append(", ")
          .append(property.getNumber())
          .append(property.getComplement() == null ? "" : " - " + property.getComplement());
           
        return sb.toString();
    }
    
}
