/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nerv.eva.model.repository;

import br.com.ma1s.eva.model.enums.PropertyStatus;
import br.com.nerv.eva.model.Property;
import java.util.List;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

/**
 *
 * @author Vitor
 */
@Repository
public interface PropertyDAO extends EntityRepository<Property, Long> {
    
    @Query(value = "FROM Property p WHERE p.address = ?1 AND p.number = ?2 "
                 + "AND p.complement = ?3 AND p.status = ?4")
    List<Property> find(String address, String number, String complement, PropertyStatus status);
    
    @Query(value = "FROM Property p WHERE p.address = ?1 AND p.number = ?2 "
                 + "AND p.status = ?3")
    List<Property> find(String address, String number, PropertyStatus status);
}