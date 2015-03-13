/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.service;

import br.com.ma1s.eva.exception.BusinessException;
import br.com.ma1s.eva.model.Property;
import br.com.ma1s.eva.model.enums.PropertyStatus;
import br.com.ma1s.eva.model.enums.PropertyType;
import br.com.ma1s.eva.model.repository.PropertyDAO;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

/**
 *
 * @author Vitor
 */
@RequestScoped
public class PropertyService {
    
    @Inject private EntityManager manager;
    @Inject private PropertyDAO dao;
    
    @Transactional
    public Property newProperty(Property property) {
        try {
            if (exists(property))
                throw new BusinessException("Imóvel já existente");
            else
                return dao.save(property);
        } catch (Exception e) {
            throw new BusinessException("Erro ao inserir imóvel", e);
        }
    }
    
    @Transactional
    public Property update(Property property) {
        try {   
            return dao.save(property);
        } catch (Exception e) {
            throw new BusinessException("Erro ao atualizar imóvel", e);
        }
    }
    
    public boolean exists(Property property) {
        final String address = property.getAddress();
        final String number = property.getNumber();
        final String complement = property.getComplement();
        final PropertyStatus status = property.getStatus();
        
        if (complement != null)
            return !dao.find(address, number, complement, status).isEmpty();
        else
            return !dao.find(address, number, status).isEmpty();
    }
    
    public void updateStatus(PropertyStatus status, Property property) {
        property.setStatus(status);
        update(property);
    }
    
    @Transactional
    public void remove(Property property) {
        try {
            property = manager.getReference(Property.class, property.getId());
            dao.remove(property);
        } catch (Exception e) {
            throw new BusinessException("Erro ao remover imóvel", e);
        }
    }
    
    public Property getUpdated(Long id) {
        return manager.getReference(Property.class, id);
    }
 }