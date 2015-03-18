/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.service;

import br.com.ma1s.eva.model.PaymentRegister;
import br.com.ma1s.eva.model.enums.PaymentStatus;
import br.com.ma1s.eva.model.repository.PaymentRegisterDAO;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.joda.time.DateTime;

/**
 *
 * @author Vitor
 */
@RequestScoped
public class PaymentService {
    private static final int MAX = 10;
    private static final BigInteger MULTIPLIER = new BigInteger("100");
    private final Date monthBegin;
    private final Date monthEnd;
    @Inject private PaymentRegisterDAO paymentDAO;

    public PaymentService() {
        final DateTime dt = new DateTime().dayOfMonth().setCopy(1);
        this.monthBegin = dt.toDate();
        this.monthEnd = dt.plusDays(30).toDate();
    }
    
    public long countPendents() {
        return paymentDAO.getPendentsUntilToday(PaymentStatus.PENDENT, monthEnd)
                         .count();
    }
    
    public long countMonthPendents() {
        return paymentDAO.findByStatusEqualAndDateBetween(PaymentStatus.PENDENT, monthBegin, monthEnd).count();
    }
    
    public List<PaymentRegister> getPendents() {
        return paymentDAO.findByStatusEqual(PaymentStatus.PENDENT)
                         .getResultList();
    }
    
    public List<PaymentRegister> getPendents(int page, int max) {
        return paymentDAO.getPendentsUntilToday(PaymentStatus.PENDENT, monthEnd)
                         .withPageSize(max)
                         .toPage(page)
                         .getResultList();
    }
    
    public List<PaymentRegister> getFirstPendents() {
        return paymentDAO.findByStatusEqualAndDateBetween(PaymentStatus.PENDENT, monthBegin, monthEnd)
                         .maxResults(MAX)
                         .getResultList();
    }
    
    public int getPendentPercentual() {
        final BigInteger total = new BigInteger(paymentDAO.count().toString());
        
        if (!total.equals(BigInteger.ZERO)) {
            return new BigInteger(String.valueOf(countPendents()))
                        .multiply(MULTIPLIER)
                        .divide(total)
                        .intValue();
        } else 
            return BigInteger.ZERO.intValue();
    }
    
}
