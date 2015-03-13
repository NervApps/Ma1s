/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.web.beans;

import br.com.ma1s.eva.model.Customer;
import br.com.ma1s.eva.model.Property;
import br.com.ma1s.eva.model.PropertyCustomer;
import br.com.ma1s.eva.service.PropertyCustomerService;
import br.com.ma1s.eva.web.beans.common.ConversationBean;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;

/**
 *
 * @author Vitor
 */
@Named("lockBean")
public class PropertyLockBean extends ConversationBean implements Serializable {
    private static final String SEARCH_PAGE = "property_search?faces-redirect=true";
    private static final String PARAM_NAME = "property";
    private Property property;
    
    @Getter private PropertyCustomer propertyCustomer;
    @Inject private PropertyCustomerService service;
    
    @PostConstruct
    public void init() {
        propertyCustomer = new PropertyCustomer();
        propertyCustomer.setCustomer(new Customer());
        
        final Property param = getParam(PARAM_NAME, Property.class);
        if (param != null) {
            this.property = param;
            initConversation();
        }
    }
    
    public String toContract() {
        if (property != null) {
            propertyCustomer.setProperty(property);
            return toStep(2, "lock_property_contract");
        } else {
            error("Nenhum imóvel selecionado", "Escolha um imóvel ");
            return cancel();
        }
    }
    
    public String confirm() {
        service.lock(propertyCustomer);
        info("Imóvel reservado com sucesso");
        return cancel();
    }
    
    public String cancel() {
        endConversation();
        return SEARCH_PAGE;
    }
}
