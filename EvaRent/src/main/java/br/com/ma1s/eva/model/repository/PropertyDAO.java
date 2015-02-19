/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.model.repository;

import br.com.ma1s.eva.model.Property;
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
                 + "AND p.complement = ?3")
    List<Property> find(String address, String number, String complement);
    
    List<Property> findByAddressEqualAndNumberEqual(String address, String number);
}