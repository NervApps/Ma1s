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
import br.com.ma1s.eva.service.qualifier.Remove;
import br.com.ma1s.eva.service.qualifier.Sell;
import br.com.ma1s.eva.service.qualifier.UpdateStatus;
import br.com.ma1s.eva.service.validation.PropertyValidation;
import br.com.ma1s.eva.service.validation.ValidationFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

/**
 *
 * @author Vitor
 */
@RequestScoped
public class PropertyCustomerService {
    private PropertyCustomer pc;
    
    @Inject @Deposit private Event<PropertyCustomer> rents;
    @Inject @Sell private Event<PropertyCustomer> sells;
    @Inject @UpdateStatus private Event<Property> event;
    @Inject private PropertyCustomerDAO dao;
    @Inject private EntityManager manager;
    
    public void lock(PropertyCustomer pc) {
        this.pc = pc;
        final Property p = pc.getProperty();
        
        if (PropertyStatus.ONLY_PURCHASE.equals(p.getStatus()))
            process(sells);
        else
            process(rents);
    }
    
    public List<PropertyCustomer> getLocked(int page) {
        final List<PropertyCustomer> locked = new ArrayList<>();
        locked.addAll(getByStatusPaging(page, PropertyStatus.RENTING));
        locked.addAll(getByStatusPaging(page, PropertyStatus.SELLING));
        return locked;
    }
    
    public List<PropertyCustomer> getByStatusPaging(int page, PropertyStatus status) {
        final int max = 10;
        return dao.getByStatus(PropertyStatus.RENTING)
                           .firstResult(page)
                           .maxResults(max)
                           .getResultList();
    }
    
    private void process(Event<PropertyCustomer> trigger) {
        beforeInsert();
        insert();
        afterInsert(trigger);
        event.fire(pc.getProperty());
    }
    
    private void beforeInsert() {
        final PropertyValidation pv = ValidationFactory.propertyValidation(pc);
        if (pv != null)
            pv.validate();
        
        pc.setStartedDate(new Date());
    }

    @Transactional
    private void insert() {
        pc = dao.save(pc);
    }
    
    private void afterInsert(Event event) {
        event.fire(pc);
    }
    
    @Transactional
    public void remove(@Observes @Remove PropertyCustomer propertyCustomer) {
        propertyCustomer = manager.getReference(PropertyCustomer.class, propertyCustomer.getId());
        dao.remove(propertyCustomer);
    }
}