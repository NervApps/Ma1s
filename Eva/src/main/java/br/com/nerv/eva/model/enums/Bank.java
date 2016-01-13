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
public enum Bank {
    SANTANDER(033),
    BRADESCO(237),
    CITIBANK(477),
    BANCO_DO_BRASIL(001),
    ITAU(341),
    HSBC(399),
    CAIXA(104);    
    
    @Getter private final int code;

    private Bank(int code) {
        this.code = code;
    }
}
