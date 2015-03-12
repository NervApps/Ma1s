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
    ONLY_PURCHASE("Compra"),
    ONLY_RENT("Aluguel"),
    RENTING("Alugando"),
    RENTED("Alugada"),
    SELLING("Vendendo"),
    SELLED("Vendida");
    
    @Getter private final String label;

    private PropertyStatus(final String label) {
        this.label = label;
    }
}
