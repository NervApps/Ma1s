/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.web.beans;

import br.com.ma1s.eva.model.PaymentRegister;
import br.com.ma1s.eva.model.PropertyCustomer;
import br.com.ma1s.eva.service.PaymentService;
import br.com.ma1s.eva.service.PropertyCustomerService;
import br.com.ma1s.eva.web.beans.common.ManagedBean;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor
 */
@Named @RequestScoped
public class PendentContractsBean extends ManagedBean {
    private static final String PARAM_PROPERTY = "property";
    
    @Getter private int page = 0;
    @Getter private final int interval = 20;
    @Getter @Setter private PaymentRegister selected;
    @Getter private List<PaymentRegister> pendents;
    @Inject private PaymentService service;

    @PostConstruct
    public void init() {
        pendents = new ArrayList<>();
        search();
    }
    
    public void next() {
        page += interval;
        search();
    }
    
    public void previous() {
        page = page > interval ? page - interval : 0;
        search();
    }
    
    private void search() {
        pendents.clear();
        pendents.addAll(service.getPendents(page, interval));
    }
    
    public void propertyDetail() {
        putParam(PARAM_PROPERTY, selected.getPropertyCustomer().getProperty());
        putParam(PARAM_FROM_PAGE, "contract_pendents");
        toPage("property_detail", true);
    }
}
