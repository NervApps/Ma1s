/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.model.repository.util;

import br.com.ma1s.eva.web.util.SearchParam;
import br.com.ma1s.eva.model.repository.query.SearchQueryTranslator;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Vitor
 */
@RequestScoped
public class SearchDAO {
    
    @Inject private EntityManager manager;
    
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
    
    public List find(SearchQueryTranslator translator, List<SearchParam> params) {
        final String sql = getQuery(translator, params);
        final Query query = manager.createQuery(sql);
        
        for (int i = 0; i < params.size(); i++) {
            query.setParameter(i+1, params.get(i).getValue());
        }
        return query.getResultList();
    }
    
    public List find(SearchQueryTranslator translator, List<SearchParam> params,
                     int first, int max) {
        final String sql = getQuery(translator, params);
        final Query query = manager.createQuery(sql);
        
        for (int i = 0; i < params.size(); i++) {
            query.setParameter(i+1, params.get(i).getValue());
        }
        
        return query.setFirstResult(first)
                    .setMaxResults(max)
                    .getResultList();
    }
}