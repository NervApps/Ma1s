/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.service.validation;

import br.com.ma1s.eva.exception.BusinessException;
import br.com.ma1s.eva.model.PropertyCustomer;

/**
 *
 * @author Vitor
 */
public abstract class PropertyValidation {
    protected final PropertyCustomer pc;

    public PropertyValidation(final PropertyCustomer pc) {
        this.pc = pc;
    }
    
    public void validate() throws BusinessException {
        if (pc.getProperty() == null)
            throw new BusinessException("N�o h� im�vel associado ao cliente");
        if (pc.getCustomer() == null)
            throw new BusinessException("Favor selecionar um locador");
        if (pc.getPaymentDay() == null || pc.getPaymentDay() <= 0)
            throw new BusinessException("Favor selecionar um dia de pagamento");
    }
}