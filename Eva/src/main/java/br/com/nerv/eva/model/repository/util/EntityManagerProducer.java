/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nerv.eva.model.repository.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author Vitor
 */
@RequestScoped
public class EntityManagerProducer {
    
    @PersistenceUnit(name = "eva")
    private EntityManagerFactory factory;
    
    @Produces @RequestScoped
    public EntityManager create() {
        return factory.createEntityManager();
    }
    
    public void close(@Disposes EntityManager manager) {
        if (manager.isOpen())
            manager.close();
    }
}
