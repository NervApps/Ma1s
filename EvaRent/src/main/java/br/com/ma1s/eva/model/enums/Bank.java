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
public enum Bank {
    SANTANDER(033);
    
    @Getter private final int code;

    private Bank(int code) {
        this.code = code;
    }
}
