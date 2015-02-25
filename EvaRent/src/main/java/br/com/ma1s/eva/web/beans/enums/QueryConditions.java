/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.web.beans.enums;

import lombok.Getter;

/**
 *
 * @author Vitor
 */
public enum QueryConditions {
    eq("Igual a", "="),
    gt("Maior que", ">"),
    ge("Maior ou igual a", ">="),
    ne("Diferente de", "!="),
    lt("Menor que", "<"),
    le("Menor ou igual a", "<=");
    
    @Getter private final String label;
    @Getter private final String sql;

    private QueryConditions(String label, String sql) {
        this.label = label;
        this.sql = sql;
    }
}