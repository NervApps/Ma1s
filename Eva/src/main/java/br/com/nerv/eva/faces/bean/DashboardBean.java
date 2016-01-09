/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nerv.eva.faces.bean;

import br.com.nerv.eva.faces.stereotype.RequestBean;
import br.com.nerv.eva.model.PaymentRegister;
import br.com.nerv.eva.service.PaymentService;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author vitor
 */
@RequestBean
public class DashboardBean {
    @Inject private PaymentService service;
    
    public long getPendentsCount() {
        return service.countMonthPendents();
    }
    
    public List<PaymentRegister> getPendents() {
        return service.getFirstPendents();
    }
}
