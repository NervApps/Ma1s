/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.web.beans;

import br.com.ma1s.eva.model.Property;
import br.com.ma1s.eva.model.repository.query.PropertyQueryTranslator;
import br.com.ma1s.eva.service.GenericSearchService;
import br.com.ma1s.eva.web.beans.common.ManagedBean;
import br.com.ma1s.eva.web.util.SearchParam;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor
 */
@Named @ViewScoped
public class SearchPropertyBean extends ManagedBean implements Serializable {
    private static final int max = 50;
    
    @Getter @Setter private int current = 0;
    @Getter @Setter private SearchParam param;
    @Getter private List<SearchParam> params;
    @Getter private List<Property> result;
    
    @Inject private GenericSearchService service;
    @Inject private PropertyQueryTranslator translator;
    
    @PostConstruct
    public void init() {
        param = new SearchParam();
        params = new ArrayList<>();
    }
    
    public void addQuery() {
        if (params.contains(param))
            warn("Filtro de busca já adicionado");
        else {
            params.add(param);
            param = new SearchParam();
        }
    }
    
    public void removeQuery() {
        if (param != null && params.contains(param)) {
            params.remove(param);
            param = new SearchParam();
        }
    }
    
    public void search() {
        result = service.find(Property.class, translator, params, current, max);
    }
}
