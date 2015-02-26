/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.model.repository.util;

import br.com.ma1s.eva.web.util.SearchParam;
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
    
    public List find(String sql, List<SearchParam> params) {
        final Query query = manager.createQuery(sql);
        
        for (int i = 0; i < params.size(); i++) {
            query.setParameter(i+1, params.get(i).getValue());
        }
        return query.getResultList();
    }
    
    public List find(String sql, List<SearchParam> params, int first, int max) {
        final Query query = manager.createQuery(sql);
        
        for (int i = 0; i < params.size(); i++) {
            query.setParameter(i+1, params.get(i).getValue());
        }
        
        return query.setFirstResult(first)
                    .setMaxResults(max)
                    .getResultList();
    }
}