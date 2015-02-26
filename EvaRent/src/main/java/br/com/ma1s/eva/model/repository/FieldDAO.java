/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.model.repository;

import br.com.ma1s.eva.model.Field;
import br.com.ma1s.eva.model.FieldTable;
import java.util.List;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.SingleResultType;

/**
 *
 * @author Vitor
 */
@Repository
public interface FieldDAO extends EntityRepository<FieldTable, Long> {

    @Query("SELECT e.field FROM FieldTable e WHERE e.tableName = ?1")
    public List<Field> getFieldsBy(String tableName);
    
    @Query(value = "SELECT e FROM FieldTable e WHERE e.field = ?1 AND e.tableName = ?2", 
           singleResult = SingleResultType.OPTIONAL)
    public FieldTable findBy(Field field, String tableName);
}