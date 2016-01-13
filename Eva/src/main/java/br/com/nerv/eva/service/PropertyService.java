/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nerv.eva.service;

import br.com.ma1s.eva.service.qualifier.RollbackStatus;
import br.com.ma1s.eva.service.qualifier.UpdateStatus;
import br.com.nerv.eva.exception.BusinessException;
import br.com.nerv.eva.model.Property;
import br.com.nerv.eva.model.enums.PropertyStatus;
import static br.com.nerv.eva.model.enums.PropertyStatus.ONLY_PURCHASE;
import static br.com.nerv.eva.model.enums.PropertyStatus.ONLY_RENT;
import static br.com.nerv.eva.model.enums.PropertyStatus.RENTED;
import static br.com.nerv.eva.model.enums.PropertyStatus.RENTING;
import static br.com.nerv.eva.model.enums.PropertyStatus.SELLING;
import br.com.nerv.eva.model.repository.PropertyDAO;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
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
    
    @Transactional
    public void updateStatus(@Observes @UpdateStatus Property property) {
        PropertyStatus target = null;
        final PropertyStatus status = property.getStatus();
        
        switch (status) {
            case RENTING : target = PropertyStatus.RENTED; break;
            case RENTED : target = PropertyStatus.ONLY_RENT; break;
            case SELLING : target = PropertyStatus.SELLED; break;
            case ONLY_PURCHASE : target = PropertyStatus.SELLING; break;
            case ONLY_RENT : target = PropertyStatus.RENTING; break;
            default: throw new BusinessException("Imóvel com status inválido. Contate o administrador");
        }
        
        property.setStatus(target);
        dao.save(property);
    }
    
    @Transactional
    public void rollbackStatus(@Observes @RollbackStatus Property property) {
        PropertyStatus target = null;
        final PropertyStatus status = property.getStatus();
        
        switch (status) {
            case RENTING : target = PropertyStatus.ONLY_RENT; break;
            case SELLING : target = PropertyStatus.ONLY_PURCHASE; break;
            default: throw new BusinessException("Imóvel com status inválido. Contate o administrador");
        }
        
        property.setStatus(target);
        dao.save(property);
    }
    
    public List<Property> findByAttributes(Property property) {
        return dao.find(property);
    }
 }