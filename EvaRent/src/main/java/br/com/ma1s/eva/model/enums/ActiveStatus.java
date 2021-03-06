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
public enum ActiveStatus {
    ACTIVE("Ativo"),
    INACTIVE("Inativo");
    
    @Getter private final String label;

    private ActiveStatus(String label) {
        this.label = label;
    }
}