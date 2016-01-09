/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nerv.eva.model.repository;

import br.com.ma1s.eva.model.PaymentRegister;
import br.com.ma1s.eva.model.PropertyCustomer;
import br.com.ma1s.eva.model.enums.PaymentStatus;
import java.util.Date;
import java.util.List;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryResult;
import org.apache.deltaspike.data.api.Repository;

/**
 *
 * @author Vitor
 */
@Repository
public interface PaymentRegisterDAO extends EntityRepository<PaymentRegister, Long> {
    
    QueryResult<PaymentRegister> findByStatusEqual(PaymentStatus status);
    
    QueryResult<PaymentRegister> findByStatusEqualAndDateBetween(PaymentStatus status, Date begin, Date end);
    
    @Query("select p from PaymentRegister p where p.status = ?1 and p.date <= ?2")
    QueryResult<PaymentRegister> getPendentsUntil(PaymentStatus status, Date end);
    
    @Query("select p from PaymentRegister p where p.date <= ?1")
    QueryResult<PaymentRegister> getPaymentsUntil(Date end);
    
    List<PaymentRegister> findByPropertyCustomerEqual(PropertyCustomer propertyCustomer);
}