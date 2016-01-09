/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nerv.eva.service.validation;

import static br.com.ma1s.eva.model.enums.PropertyStatus.ONLY_RENT;
import br.com.nerv.eva.model.PropertyCustomer;

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
