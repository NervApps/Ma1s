/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.model.repository.query;

import br.com.ma1s.eva.model.Property;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Vitor
 */
@RequestScoped
public class PropertyQueryTranslator extends SearchQueryTranslator<Property> {

    @Override
    public Class<Property> getType() {
        return Property.class;
    }
}
