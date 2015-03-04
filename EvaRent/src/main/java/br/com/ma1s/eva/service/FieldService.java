/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.service;

import br.com.ma1s.eva.model.Field;
import br.com.ma1s.eva.model.FieldTable;
import br.com.ma1s.eva.model.repository.FieldDAO;
import br.com.ma1s.eva.model.repository.FieldTableDAO;
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
    
    @Inject private FieldTableDAO fieldTableDAO;
    @Inject private FieldDAO fieldDAO;
    
    public List<Field> getFields(Class<?> entity) {
        final String table = getTableName(entity);
        return fieldTableDAO.getFieldsBy(table);
    }
    
    public String getColumnName(Class<?> entity, Field field) {
        final String table = getTableName(entity);
        final FieldTable ft = fieldTableDAO.findBy(field, table);
        
        return ft != null ? ft.getColumnName() : null;
    }
    
    public Field newField(Field field) {
        Field saved = fieldDAO.findByLabelEqual(field.getLabel());
        if (saved == null)
            saved = fieldDAO.save(field);
        
        return saved;
    }
    
    public void newFieldTable(FieldTable ft) {
        FieldTable saved = fieldTableDAO.findBy(ft.getField(), ft.getTableName());
        if (saved == null)
            fieldTableDAO.save(ft);
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