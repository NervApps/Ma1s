/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.service.validation;

import br.com.ma1s.eva.model.PropertyCustomer;

/**
 *
 * @author Vitor
 */
public final class ValidationFactory {
    
    public static PropertyValidation propertyValidation(final PropertyCustomer pc) {
        switch (pc.getProperty().getStatus()) {
            case ONLY_RENT : return new RentPropertyValidation(pc);
            default : return null;
        }
    }
}
