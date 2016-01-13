/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nerv.eva.service.listener;

import br.com.ma1s.eva.service.qualifier.Sell;
import br.com.nerv.eva.exception.BusinessException;
import br.com.nerv.eva.model.PaymentRegister;
import br.com.nerv.eva.model.PropertyCustomer;
import br.com.nerv.eva.service.util.ProcessPayment;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.joda.time.DateTime;

/**
 *
 * @author Vitor
 */
@RequestScoped
public class ProcessSellDeposit extends ProcessPayment {
    
    @Transactional
    public void listen(@Observes @Sell PropertyCustomer pc) {
        final String value = format(pc.getProperty().getValue());
        
        if (!value.equals(format(pc.getDepositValue()))) {
            throw new BusinessException("O valor do depósito não corresponde "
                    + "ao valor anunciado");
        }        
        
        DateTime dt = new DateTime();        
        if (dt.dayOfMonth().get() <= pc.getPaymentDay())
            dt.plusMonths(1);
        
        PaymentRegister payment = build(pc);
        payment.setDate(dt.dayOfMonth().setCopy(pc.getPaymentDay()).toDate());
        prDAO.save(payment);
    }
    
    private String format(BigDecimal value) {
        return new DecimalFormat("#,##0.00").format(value);
    }
}