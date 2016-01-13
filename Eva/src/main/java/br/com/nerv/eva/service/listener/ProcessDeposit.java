/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nerv.eva.service.listener;

import br.com.ma1s.eva.service.qualifier.Deposit;
import br.com.nerv.eva.model.PaymentRegister;
import br.com.nerv.eva.model.PropertyCustomer;
import br.com.nerv.eva.service.util.ProcessPayment;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.joda.time.DateTime;

/**
 *
 * @author Vitor
 */
@RequestScoped
public class ProcessDeposit extends ProcessPayment {
    
    @Transactional
    public void listen(@Observes @Deposit PropertyCustomer pc) {
        final BigDecimal rentValue = pc.getProperty().getValue();
        final BigInteger result = pc.getDepositValue()
                                    .divide(rentValue)
                                    .toBigInteger();
        
        for (int i = 0; i < result.intValue(); i++) {
            final PaymentRegister payment = build(pc);
            payment.setDate(new DateTime().plusMonths(i).toDate());
            prDAO.save(payment);
        }
    }
}
