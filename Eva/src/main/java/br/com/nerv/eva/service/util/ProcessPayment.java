/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nerv.eva.service.util;

import br.com.ma1s.eva.model.enums.PaymentStatus;
import br.com.nerv.eva.model.PaymentRegister;
import br.com.nerv.eva.model.PropertyCustomer;
import br.com.nerv.eva.model.repository.PaymentRegisterDAO;
import javax.inject.Inject;

/**
 *
 * @author Vitor
 */
public abstract class ProcessPayment {
    
    @Inject protected PaymentRegisterDAO prDAO;
    
    public PaymentRegister build(PropertyCustomer pc) {
        final PaymentRegister payment = new PaymentRegister();
        payment.setPropertyCustomer(pc);
        payment.setValue(pc.getProperty().getValue());
        payment.setStatus(PaymentStatus.PENDENT);
        return payment;
    }
}