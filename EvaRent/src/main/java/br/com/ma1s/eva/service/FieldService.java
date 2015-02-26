/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.service;

import br.com.ma1s.eva.model.Field;
import br.com.ma1s.eva.model.FieldTable;
import br.com.ma1s.eva.model.repository.FieldDAO;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.Table;

/**
 *
 * @author Vitor
 */
@RequestScoped
public class FieldService {
    
    @Inject private FieldDAO dao;
    
    public List<Field> getFields(Class<?> entity) {
        final String table = getTableName(entity);
        return dao.getFieldsBy(table);
    }
    
    public String getColumnName(Class<?> entity, Field field) {
        final String table = getTableName(entity);
        final FieldTable ft = dao.findBy(field, table);
        
        return ft != null ? ft.getColumnName() : null;
    }
    
    private String getTableName(Class<?> entity) {
        if (entity.isAnnotationPresent(Table.class)) {
            Table table = entity.getAnnotation(Table.class);
            return table.name();
        } else
            throw new IllegalArgumentException(entity.getName() + 
                    " doesn´t have the @Table annotation");
    }
}