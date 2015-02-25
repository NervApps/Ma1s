/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.web.beans.enums;

import java.util.Arrays;
import java.util.List;
import lombok.Getter;

/**
 *
 * @author Vitor
 */
public enum Fieldset {
    BAIRRO("Bairro", 
           "Itaquera, Artur Alvim, Tatuapé, etc", 
           "text",
           Arrays.asList(QueryConditions.eq, QueryConditions.ne)),
    
    AREA_CONSTRUIDA("Área Construída", 
                    "Em m²", 
                    "number",
                    Arrays.asList(QueryConditions.values())),
    
    COZINHA("Cozinha",
            "",
            "number",
            Arrays.asList(QueryConditions.values()));
    
    @Getter private final String label;
    @Getter private final String placeholder;
    @Getter private final String htmlInputType;
    @Getter private final List<QueryConditions> avaliableConditions;

    private Fieldset(String label, String placeholder, String htmlInputType, List<QueryConditions> avaliableConditions) {
        this.label = label;
        this.placeholder = placeholder;
        this.htmlInputType = htmlInputType;
        this.avaliableConditions = avaliableConditions;
    }
}