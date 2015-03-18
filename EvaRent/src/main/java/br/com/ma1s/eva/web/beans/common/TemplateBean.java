/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.web.beans.common;

import br.com.ma1s.eva.model.PaymentRegister;
import br.com.ma1s.eva.service.PaymentService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;

/**
 *
 * @author Vitor
 */
@Named @RequestScoped
public class TemplateBean extends ManagedBean {
    @Getter private long pendents;
    @Inject private PaymentService service;
    
    @PostConstruct
    public void init() {
        this.pendents = service.countMonthPendents();
    }
    
    public List<PaymentRegister> getFirstPendents() {
        return service.getFirstPendents();
    }
    
    public int getPendentPercentual() {
        return service.getPendentPercentual();
    }
}