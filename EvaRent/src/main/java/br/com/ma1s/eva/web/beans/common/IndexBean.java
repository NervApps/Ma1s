/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.web.beans.common;

import br.com.ma1s.eva.model.PaymentRegister;
import br.com.ma1s.eva.service.PaymentService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;

/**
 *
 * @author Vitor
 */
@Named @SessionScoped
public class IndexBean extends ManagedBean implements Serializable {
    @Getter private int pendentPercentual;
    @Inject private PaymentService service;
    
    @PostConstruct
    public void init() {
        loadPendentPercentual();
    }
    
    public long getPendents() {
        return service.countMonthPendents();
    }
    
    public List<PaymentRegister> getFirstPendents() {
        return service.getFirstPendents();
    }
    
    public void loadPendentPercentual() {
        pendentPercentual = service.getPendentPercentual();
    }
}
