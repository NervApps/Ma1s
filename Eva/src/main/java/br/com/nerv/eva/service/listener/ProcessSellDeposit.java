/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nerv.eva.service.listener;

import br.com.ma1s.eva.service.qualifier.Sell;
import br.com.nerv.eva.exception.BusinessException;
import br.com.nerv.eva.model.PropertyCustomer;
import java.math.BigDecimal;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;

/**
 *
 * @author Vitor
 */
@RequestScoped
public class ProcessSellDeposit {
    
    public void listen(@Observes @Sell PropertyCustomer pc) {
        final BigDecimal value = pc.getProperty().getValue();
        
        if (!value.equals(pc.getDepositValue())) {
            throw new BusinessException("O valor do depósito não corresponde "
                    + "ao valor anunciado");
        }
    }
}