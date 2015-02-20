/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.service;

import br.com.ma1s.eva.exception.BusinessException;
import br.com.ma1s.eva.model.Property;
import br.com.ma1s.eva.model.repository.PropertyDAO;
import java.io.IOException;
import java.io.InputStream;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Vitor
 */
@RequestScoped
public class PropertyService {
    
    @Inject private PropertyDAO dao;
    
    public void save(final Property property) {
        try {
            if (exists(property))
                throw new BusinessException("Imóvel já existente");
            else
                dao.save(property);
        } catch (Exception e) {
            throw new BusinessException("Erro ao inserir imóvel", e);
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