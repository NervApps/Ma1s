/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.model.repository;

import br.com.ma1s.eva.model.Field;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.SingleResultType;

/**
 *
 * @author Vitor
 */
@Repository
public interface FieldDAO extends EntityRepository<Field, Long> {
    
    @Query(singleResult = SingleResultType.OPTIONAL)
    Field findByLabelEqual(String label);
    
}
