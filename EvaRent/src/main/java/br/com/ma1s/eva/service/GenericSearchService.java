/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.service;

import br.com.ma1s.eva.model.repository.query.SearchQueryTranslator;
import br.com.ma1s.eva.model.repository.SearchDAO;
import br.com.ma1s.eva.web.util.SearchParam;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Vitor
 * @param <T> A entidade correspondente ao retorno
 * da busca genérica.
 */
public class GenericSearchService<T> implements Serializable {
    
    @Inject private SearchDAO dao;
    
    public List<T> find(SearchQueryTranslator translator, 
                            List<SearchParam> params) {
        
        final String query = getQuery(translator, params);
        return dao.find(query, params);
    }
    
    public List<T> find(SearchQueryTranslator translator, 
                            List<SearchParam> params,
                            int first, int max) {
     
        final String query = getQuery(translator, params);
        return dao.find(query, params, first, max);
    }
    
    private String getQuery(SearchQueryTranslator translator, List<SearchParam> params) {
        
        final StringBuilder sb = new StringBuilder();
        sb.append("SELECT e FROM ")
          .append(translator.getType().getSimpleName())
          .append(" e WHERE ");
        
        for (int i = 0; i < params.size(); i++) {
            final SearchParam param = params.get(i);
            
            if (i > 0) 
                sb.append(" AND ");
            
            sb.append(translator.getAttribute(param.getField()))
              .append(" ")
              .append(param.getCondition().getSql())
              .append(i+1);
            
        }
        return sb.toString();
    }
}