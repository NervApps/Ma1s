/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.service;

import br.com.ma1s.eva.model.Filter;
import br.com.ma1s.eva.model.repository.FilterDAO;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Vitor
 */
@RequestScoped
public class FilterService {
    
    @Inject private FilterDAO dao;
    
    public Filter newFilter(Filter filter) {
        return dao.save(filter);
    }
}