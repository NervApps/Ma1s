/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.web.util;

import br.com.ma1s.eva.model.Field;
import br.com.ma1s.eva.model.Filter;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor
 */
@EqualsAndHashCode
public class SearchParam implements Serializable {
    @Getter @Setter private Field field;
    @Getter @Setter private Filter condition;
    @Setter private Object value;
    @Setter private Object value2;

    public Object getValue() {
        return parseValue(value);
    }

    public Object getValue2() {
        return parseValue(value2);
    }
    
    private Object parseValue(Object value) {
        if (value != null && field.getInputType() != null) {
            switch (field.getInputType()) {
            case "text":
                return value.toString();
            case "number":
                return Integer.parseInt(value.toString());
            default:
                return value;
            }
        } else
            return value;
    }
}