/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.service.util;

import br.com.ma1s.eva.model.repository.PermissionDAO;
import br.com.ma1s.eva.qualifier.BlockedPages;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 *
 * @author Vitor
 */
public class CommonQueriesProducer implements Serializable {
    
    @Produces @BlockedPages @ApplicationScoped
    public List<String> getBlockedPages(PermissionDAO dao) {
        return dao.getProtectedPages();
    }
}