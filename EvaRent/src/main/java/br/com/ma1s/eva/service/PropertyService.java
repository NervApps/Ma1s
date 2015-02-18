/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.service;

import br.com.ma1s.eva.model.Property;
import java.io.IOException;
import java.io.InputStream;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Vitor
 */
@RequestScoped
public class PropertyService {
    
    public void save(final Property property) {
        
    }
    
    public void save(final Property property, final InputStream photo) 
            throws IOException {
        
        property.buildPhoto(photo);
        save(property);
    }
}
