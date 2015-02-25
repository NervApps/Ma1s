/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.service;

import br.com.ma1s.eva.model.repository.query.SearchQueryTranslator;
import br.com.ma1s.eva.model.repository.util.SearchDAO;
import br.com.ma1s.eva.web.util.SearchParam;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Vitor
 */
@RequestScoped
public class GenericSearchService {
    
    @Inject private SearchDAO dao;
    
    public <T> List<T> find(Class<T> type, 
                            SearchQueryTranslator translator, 
                            List<SearchParam> params) {
        
        return dao.find(translator, params);
    }
    
    public <T> List<T> find(Class<T> type, 
                            SearchQueryTranslator translator, 
                            List<SearchParam> params,
                            int first, int max) {
        
        return dao.find(translator, params, first, max);
    }
}