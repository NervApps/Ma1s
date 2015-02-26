/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.model.repository.query;

import br.com.ma1s.eva.model.Field;
import br.com.ma1s.eva.service.FieldService;
import javax.inject.Inject;
import javax.persistence.Column;

/**
 *
 * @author Vitor
 * @param <T> A entidade tratada na busca
 */
public abstract class SearchQueryTranslator<T> {
    
    @Inject private FieldService service;
    
    /**
     * Retorna o nome do atributo da entidade
     * correspondente.
     * @param field O nome do campo que foi usado
     * na busca.
     * @return O nome do atributo correspondente
     * na entidade.
     */
    public String getAttribute(final Field field) {
        final String columnName = service.getColumnName(getType(), field);
 
        if (columnName != null) {
            for (java.lang.reflect.Field attr : getType().getDeclaredFields()) {
                if (attr.isAnnotationPresent(Column.class)) {
                    final Column column = attr.getAnnotation(Column.class);

                    if (columnName.equals(column.name()))
                        return attr.getName();
                }
            }
        }
        return null;
    }
    
    /**
     * Define o tipo da entidade que será usada na query.
     * @return A classe da entidade.
     */
    public abstract Class<T> getType();
}