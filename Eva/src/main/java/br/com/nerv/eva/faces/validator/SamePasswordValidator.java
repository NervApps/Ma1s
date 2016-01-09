/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nerv.eva.faces.validator;

import br.com.nerv.eva.faces.util.Messages;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Vitor
 */
@Named("samePwdValidator") 
@RequestScoped
public class SamePasswordValidator {
    
    @Inject private Messages msg;
    private String source;
    private String target;
    
    public void validateTarget(FacesContext ctx, UIComponent comp, Object value) {
        target = value.toString();
        validate();
    }
    
    public void validateSource(FacesContext ctx, UIComponent comp, Object value) {
        source = value.toString();
        validate();
    }
    
    private void validate() {
        if (source != null && target != null) {
            if (!target.equals(source)) {
                msg.error("As senhas devem ser iguais");
            }    
        }
    }
}