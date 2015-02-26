/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.web.beans.common;

import br.com.ma1s.eva.model.Field;
import br.com.ma1s.eva.model.Filter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author Vitor
 */
@Named("searchParams") @ApplicationScoped
public class SearchParamsBean implements Serializable {
    
//    @Inject private FieldService service;
    
    public List<Field> getPropertySearchFields() {
//        return service.getFields(Property.class);
        return getPropertyFieldsMock();
    }
    
    private List<Field> getPropertyFieldsMock() {
        final List<Field> fields = new ArrayList<>();
        fields.add(new Field("Bairro", "Itaquera, Artur Alvim, Tatuapé, etc", "text", getBasicFilters()));
        fields.add(new Field("Área Constrúida", "Em m²", "number", getAllFilters()));
        fields.add(new Field("Cozinha", "", "number", getAllFilters()));
        
        return fields;
    }
    
    private List<Filter> getAllFilters() {
        final List<Filter> filters = new ArrayList<>();
        filters.add(new Filter("Igual a", "="));
        filters.add(new Filter("Maior que", ">"));
        filters.add(new Filter("Maior ou igual a", ">="));
        filters.add(new Filter("Diferente de", "!="));
        filters.add(new Filter("Menor que", "<"));
        filters.add(new Filter("Menor ou igual a", "<="));
        return filters;
    }
    
    private List<Filter> getBasicFilters() {
        final List<Filter> filters = new ArrayList<>();
        filters.add(new Filter("Igual a", "="));
        filters.add(new Filter("Diferente de", "!="));
        return filters;
    }
}