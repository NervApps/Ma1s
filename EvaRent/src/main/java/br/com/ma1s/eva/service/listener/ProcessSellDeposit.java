/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.service.listener;

import br.com.ma1s.eva.exception.BusinessException;
import br.com.ma1s.eva.model.PropertyCustomer;
import br.com.ma1s.eva.service.qualifier.Sell;
import java.math.BigDecimal;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;

/**
 *
 * @author Vitor
 */
@Stateless
public class ProcessSellDeposit {
    
    @Asynchronous
    public void listen(@Observes @Sell PropertyCustomer pc) {
        final BigDecimal value = pc.getProperty().getValue();
        
        if (!value.equals(pc.getDepositValue())) {
            throw new BusinessException("O valor do depósito não corresponde "
                    + "ao valor anunciado");
        }
    }
}