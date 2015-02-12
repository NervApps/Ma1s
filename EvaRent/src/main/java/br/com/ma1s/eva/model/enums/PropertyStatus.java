/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.model.enums;

import lombok.Getter;

/**
 *
 * @author Vitor
 */
public enum PropertyStatus {
    AVALIABLE("Disponível"),
    RENTED("Alugada"),
    ONLY_PURCHASE("Somente Compra"),
    ONLY_RENT("Somente Aluguel");
    
    @Getter private final String label;

    private PropertyStatus(final String label) {
        this.label = label;
    }
}
