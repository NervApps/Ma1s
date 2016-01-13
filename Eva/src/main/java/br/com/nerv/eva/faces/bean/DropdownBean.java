/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nerv.eva.faces.bean;

import br.com.nerv.eva.faces.stereotype.RequestBean;
import br.com.nerv.eva.model.enums.PropertyStatus;
import br.com.nerv.eva.model.enums.PropertyType;
import java.util.Arrays;
import java.util.List;

/**
 * Bean que encapsula os combo box utilizados em mais de uma tela
 * @author vitor
 */
@RequestBean
public class DropdownBean {
    
    public List<PropertyType> getTypes() {
        return Arrays.asList(PropertyType.values());
    }
    
    public List<PropertyStatus> getStatus() {
        return Arrays.asList(PropertyStatus.values());
    }
    
}
