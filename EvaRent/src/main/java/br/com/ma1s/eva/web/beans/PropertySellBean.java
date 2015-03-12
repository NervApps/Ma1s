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
import br.com.ma1s.eva.web.beans.common.ManagedBean;
import br.com.ma1s.eva.web.util.Message;
import br.com.ma1s.eva.web.util.MessageType;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;

/**
 *
 * @author Vitor
 */
@Named @ViewScoped
public class PropertySellBean extends ManagedBean implements Serializable {
    private static final String SEARCH_PAGE = "property_search";
    private static final String PARAM_NAME = "property";
    @Getter private PropertyCustomer propertyCustomer;
    @Getter private Customer customer;
    
    @Inject private PropertyCustomerService service;
    
    @PostConstruct
    public void init() {
        final Property param = getParam(PARAM_NAME, Property.class);
        if (param != null) {
            propertyCustomer = new PropertyCustomer();
            propertyCustomer.setProperty(param);
            propertyCustomer.setCustomer(new Customer());
        } else
            toPage(SEARCH_PAGE, true);
    }
    
    public String lock() {
        service.lock(propertyCustomer);
        
        final Message msg = new Message(MessageType.INFO, "Imóvel reservado com sucesso");
        msg.show();
        return SEARCH_PAGE;
    }
}
