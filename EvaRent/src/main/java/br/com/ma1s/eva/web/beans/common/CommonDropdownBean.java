/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.web.beans.common;

import br.com.ma1s.eva.model.enums.Bank;
import br.com.ma1s.eva.model.enums.PropertyStatus;
import br.com.ma1s.eva.model.enums.PropertyType;
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
    @Getter private List<Bank> bankList;
    @Getter private List<PropertyType> propertyTypes;
    
    @PostConstruct
    public void init() {
        loadPropertyStatus();
        loadPropertyTypes();
        loadBankList();
    }
    
    private void loadPropertyStatus() {
        propertyStatus = new ArrayList<>();
        propertyStatus.addAll(Arrays.asList(PropertyStatus.values()));
    }
    
    private void loadBankList() {
        bankList = new ArrayList<>();
        bankList.addAll(Arrays.asList(Bank.values()));
    }
    
    private void loadPropertyTypes() {
        propertyTypes = new ArrayList<>();
        propertyTypes.addAll(Arrays.asList(PropertyType.values()));
    }
}