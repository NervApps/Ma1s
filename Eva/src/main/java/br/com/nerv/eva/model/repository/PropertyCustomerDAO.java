/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nerv.eva.model.repository;

import br.com.ma1s.eva.model.enums.PropertyStatus;
import br.com.nerv.eva.model.PropertyCustomer;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryResult;
import org.apache.deltaspike.data.api.Repository;

/**
 *
 * @author Vitor
 */
@Repository
public interface PropertyCustomerDAO extends EntityRepository<PropertyCustomer, Long> {
    
    @Query("select p from PropertyCustomer p where p.property.status = ?1")
    QueryResult<PropertyCustomer> getByStatus(PropertyStatus status);
}