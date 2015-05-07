/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.web.beans.common;

import br.com.ma1s.eva.model.Field;
import br.com.ma1s.eva.model.FieldTable;
import br.com.ma1s.eva.model.Filter;
import br.com.ma1s.eva.model.Property;
import br.com.ma1s.eva.service.FieldService;
import br.com.ma1s.eva.service.FilterService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Table;

/**
 *
 * @author Vitor
 */
@Named("searchParams") @ApplicationScoped
public class SearchParamsBean implements Serializable {
    
    @Inject private FieldService fieldService;
    @Inject private FilterService filterService;
    
    @PostConstruct
    public void init() {
        createFieldsAndFiltersForProperty();
    }
    
    public List<Field> getPropertySearchFields() {
        return fieldService.getFields(Property.class);
    }
    
    private void createFieldsAndFiltersForProperty() {
        List<Filter> filters = getOneFilter();
        Field field = fieldService.newField(new Field("C�digo", "C�digo do im�vel", "number", filters));
        createPropertyFieldMap(field, "PROPERTY_ID");
        
        filters = getBasicFilters();
        field = fieldService.newField(new Field("Bairro", "Itaquera, Artur Alvim, Tatuap�, etc", "text", filters));
        createPropertyFieldMap(field, "NEIGHBORHOOD");
        
        filters = getAllFilters();
        field = fieldService.newField(new Field("�rea Constr�ida", "Em m�", "number", filters));
        createPropertyFieldMap(field, "AREA");
        
        field = fieldService.newField(new Field("Cozinha", "", "number", filters));
        createPropertyFieldMap(field, "KITCHEN");
     
        filters = getOneFilter();
        field = fieldService.newField(new Field("Tipo de Im�vel", "", "combo", filters));
        createPropertyFieldMap(field, "PROPERTY_TYPE");
    }
    
    private List<Filter> getAllFilters() {
        final List<Filter> inserted = new ArrayList<>();
        final List<Filter> filters = new ArrayList<>();
        filters.add(new Filter("Igual a", "="));
        filters.add(new Filter("Maior que", ">"));
        filters.add(new Filter("Maior ou igual a", ">="));
        filters.add(new Filter("Diferente de", "!="));
        filters.add(new Filter("Menor que", "<"));
        filters.add(new Filter("Menor ou igual a", "<="));
        
        for (Filter filter : filters) {
            inserted.add(filterService.newFilter(filter));
        }
        
        return inserted;
    }
    
    private List<Filter> getBasicFilters() {
        final List<Filter> inserted = new ArrayList<>();
        final List<Filter> filters = new ArrayList<>();
        filters.add(new Filter("Igual a", "="));
        filters.add(new Filter("Diferente de", "!="));
        
        for (Filter filter : filters) {
            inserted.add(filterService.newFilter(filter));
        }
        
        return inserted;
    }
    
    private List<Filter> getOneFilter() {
        final Filter filter = new Filter("Igual a", "=");
        return Arrays.asList(filterService.newFilter(filter));
    }
    
    private void createPropertyFieldMap(final Field field, final String column) {
        final FieldTable ft = new FieldTable();
        ft.setField(field);
        ft.setTableName(Property.class.getAnnotation(Table.class).name());
        ft.setColumnName(column);
        fieldService.newFieldTable(ft);
    }
}