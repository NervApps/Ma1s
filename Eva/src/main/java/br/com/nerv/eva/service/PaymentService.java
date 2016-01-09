/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nerv.eva.service;

import br.com.ma1s.eva.model.enums.PaymentStatus;
import br.com.ma1s.eva.service.qualifier.Remove;
import br.com.ma1s.eva.service.qualifier.RollbackStatus;
import br.com.ma1s.eva.service.qualifier.UpdateStatus;
import br.com.nerv.eva.model.PaymentRegister;
import br.com.nerv.eva.model.Property;
import br.com.nerv.eva.model.PropertyCustomer;
import br.com.nerv.eva.model.repository.PaymentRegisterDAO;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import org.apache.deltaspike.jpa.api.transaction.Transactional;
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
    @Inject private PaymentRegisterDAO dao;
    @Inject private EntityManager manager;
    @Inject @UpdateStatus private Event<Property> update;
    @Inject @RollbackStatus private Event<Property> rollback;
    @Inject @Remove private Event<PropertyCustomer> remove;

    public PaymentService() {
        final DateTime dt = new DateTime().dayOfMonth().setCopy(1);
        this.monthBegin = dt.toDate();
        this.monthEnd = dt.plusDays(30).toDate();
    }
    
    @Transactional
    public void approve(PaymentRegister payment) {
        payment.setStatus(PaymentStatus.APPROVED);
        dao.save(payment);
        update.fire(payment.getPropertyCustomer().getProperty());
    }
    
    @Transactional
    public void refuse(PaymentRegister payment) {
        rollback.fire(payment.getPropertyCustomer().getProperty());
        
        final List<PaymentRegister> payments = dao.findByPropertyCustomerEqual(payment.getPropertyCustomer());
        for (PaymentRegister p : payments) {
            p = manager.getReference(PaymentRegister.class, p.getId());
            dao.remove(p);
        }
        
        remove.fire(payment.getPropertyCustomer());
    }
    
    public long countPendents() {
        return dao.getPendentsUntil(PaymentStatus.PENDENT, monthEnd)
                         .count();
    }
    
    public long countMonthPendents() {
        return dao.findByStatusEqualAndDateBetween(PaymentStatus.PENDENT, monthBegin, monthEnd)
                         .count();
    }
    
    public List<PaymentRegister> getPendents(int page, int max) {
        return dao.getPendentsUntil(PaymentStatus.PENDENT, monthEnd)
                         .withPageSize(max)
                         .toPage(page)
                         .getResultList();
    }
    
    public List<PaymentRegister> getFirstPendents() {
        return dao.findByStatusEqualAndDateBetween(PaymentStatus.PENDENT, monthBegin, monthEnd)
                         .maxResults(MAX)
                         .getResultList();
    }
    
    public int getPendentPercentual() {
        final BigInteger total = new BigInteger(String.valueOf(dao.getPaymentsUntil(monthEnd).count()));
        
        if (!total.equals(BigInteger.ZERO)) {
            return new BigInteger(String.valueOf(countPendents()))
                        .multiply(MULTIPLIER)
                        .divide(total)
                        .intValue();
        } else 
            return BigInteger.ZERO.intValue();
    }
}
