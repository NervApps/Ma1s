/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.web.beans.common;

import br.com.ma1s.eva.model.enums.PropertyStatus;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import lombok.Getter;

/**
 *
 * @author Vitor
 */
@Named("dropdownBean")
@ApplicationScoped
public class CommonDropdownBean implements Serializable {
    
    @Getter private List<PropertyStatus> propertyStatus;
    
    @PostConstruct
    public void init() {
        loadPropertyStatus();
    }
    
    private void loadPropertyStatus() {
        propertyStatus = new ArrayList<>();
        propertyStatus.addAll(Arrays.asList(PropertyStatus.values()));
    }
}