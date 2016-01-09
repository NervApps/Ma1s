/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nerv.eva.model.enums;

import lombok.Getter;

/**
 *
 * @author Vitor
 */
public enum PropertyType {
    CASA("Casa"),
    APARTAMENTO("Apartamento"),
    SOBRADO("Sobrado"),
    DUPLEX("Duplex");
    
    @Getter private final String label;

    private PropertyType(final String label) {
        this.label = label;
    }
}
