/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nerv.eva.faces.converter;

import br.com.nerv.eva.util.Hasher;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.deltaspike.core.api.provider.BeanProvider;

/**
 * Conversor para campo senha.
 * @author Vitor
 */
@FacesConverter("pwdConverter")
public class PasswordConverter implements Converter {
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Hasher hasher = BeanProvider.getContextualReference(Hasher.class);
        String encoded = hasher.hash(value);
        return encoded.isEmpty() ? null : encoded;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();
    }
}