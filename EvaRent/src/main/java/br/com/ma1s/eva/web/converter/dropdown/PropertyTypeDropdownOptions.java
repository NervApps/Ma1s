/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.web.converter.dropdown;

import br.com.ma1s.eva.model.enums.PropertyType;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Vitor
 */
public class PropertyTypeDropdownOptions implements DropdownOptions<PropertyType> {

    @Override
    public List<PropertyType> getOptions() {
        return Arrays.asList(PropertyType.values());
    }

    @Override
    public PropertyType convert(Object value) {
        return PropertyType.valueOf(value.toString());
    }
}