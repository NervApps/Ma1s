/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.service;

import br.com.ma1s.eva.model.Property;
import br.com.ma1s.eva.model.PropertyCustomer;
import br.com.ma1s.eva.model.enums.PropertyStatus;
import br.com.ma1s.eva.model.repository.PropertyCustomerDAO;
import br.com.ma1s.eva.service.qualifier.Deposit;
import br.com.ma1s.eva.service.qualifier.Sell;
import br.com.ma1s.eva.service.validation.PropertyValidation;
import br.com.ma1s.eva.service.validation.ValidationFactory;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 *
 * @author Vitor
 */
@RequestScoped
public class PropertyCustomerService {
    private PropertyCustomer pc;
    
    @Inject @Deposit private Event<PropertyCustomer> rents;
    @Inject @Sell private Event<PropertyCustomer> sells;
    @Inject private PropertyCustomerDAO pcDAO;
    @Inject private PropertyService service;
    
    public void lock(PropertyCustomer pc) {
        this.pc = pc;
        final Property p = pc.getProperty();
        
        if (PropertyStatus.ONLY_PURCHASE.equals(p.getStatus()))
            sell();
        else
            rent();
    }
   
    private void rent() {
        beforeInsert();
        insert();
        afterInsert(rents);
        updateStatus(PropertyStatus.RENTING);
    }
    
    private void sell() {
        beforeInsert();
        insert();
        afterInsert(sells);
        updateStatus(PropertyStatus.SELLING);
    }
    
    private void beforeInsert() {
        final PropertyValidation pv = ValidationFactory.propertyValidation(pc);
        if (pv != null)
            pv.validate();
    }
    
    private void insert() {
        pc = pcDAO.save(pc);
    }
    
    private void afterInsert(Event event) {
        event.fire(pc);
    }
    
    private void updateStatus(PropertyStatus status) {
        service.updateStatus(status, pc.getProperty());
    }
}