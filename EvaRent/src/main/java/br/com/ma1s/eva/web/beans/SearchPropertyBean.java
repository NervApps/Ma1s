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
    @Getter @Setter private int current = 0;
    @Getter @Setter private SearchParam param;
    @Getter private List<SearchParam> params;
    @Getter private List<Property> result;
    @Getter private int first = 0;
    @Getter private final int max = 6;
    @Getter private final int interval = 7;
    
    @Inject private GenericSearchService<Property> service;
    @Inject private PropertyQueryTranslator translator;
    
    @PostConstruct
    public void init() {
        param = new SearchParam();
        params = new ArrayList<>();
        result = new ArrayList();
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
            
            if (!result.isEmpty())
                search();
        }
    }
    
    public void search() {
        first = 0;
        result.clear();
        result.addAll(find());
        
        if (result.isEmpty())
            warn("Sua pesquisa não retornou resultados");
    }
    
    public void next() {
        int previousValue = first;
        first += interval;
        final List<Property> found = find();
        
        if (found.isEmpty()) {
            warn("Não existem mais resultados");
            first = previousValue;
        } else {
            result.clear();
            result.addAll(found);
        }
    }
    
    public void previous() {
        first = first >= max ? first-interval : 0;
        result.clear();
        result.addAll(find());
    }
    
    private List<Property> find() {
        return service.find(translator, params, first, max);
    }
}
