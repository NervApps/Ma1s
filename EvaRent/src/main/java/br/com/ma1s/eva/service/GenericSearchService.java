/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.service;

import br.com.ma1s.eva.model.repository.query.SearchQueryTranslator;
import br.com.ma1s.eva.model.repository.util.SearchDAO;
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
        
        return dao.find(translator, params);
    }
    
    public List<T> find(SearchQueryTranslator translator, 
                            List<SearchParam> params,
                            int first, int max) {
        
        return dao.find(translator, params, first, max);
    }
}