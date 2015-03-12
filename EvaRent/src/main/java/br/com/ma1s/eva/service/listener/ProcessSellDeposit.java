/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.service.listener;

import br.com.ma1s.eva.model.PropertyCustomer;
import br.com.ma1s.eva.service.qualifier.Sell;
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
        //TODO: inserir o recebimento do pagamento
    }
}