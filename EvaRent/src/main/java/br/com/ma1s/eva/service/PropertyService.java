/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.service;

import br.com.ma1s.eva.exception.BusinessException;
import br.com.ma1s.eva.model.Property;
import br.com.ma1s.eva.model.repository.PropertyDAO;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Vitor
 */
@RequestScoped
public class PropertyService {
    
    @Inject private PropertyDAO dao;
    
    public Property save(final Property property) {
        try {
            if (exists(property))
                throw new BusinessException("Imóvel já existente");
            else
                return dao.save(property);
        } catch (Exception e) {
            throw new BusinessException("Erro ao inserir imóvel", e);
        }
    }
    
    public Property update(final Property property) {
        try {   
            return dao.saveAndFlush(property);
        } catch (Exception e) {
            throw new BusinessException("Erro ao atualizar imóvel", e);
        }
    }
    
    public boolean exists(final Property property) {
        final String address = property.getAddress();
        final String number = property.getNumber();
        final String complement = property.getComplement();
        
        if (complement != null)
            return !dao.find(address, number, complement).isEmpty();
        else
            return !dao.findByAddressEqualAndNumberEqual(address, number).isEmpty();
    }
 }