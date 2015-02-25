/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.web.beans.common;

import br.com.ma1s.eva.web.beans.enums.Fieldset;
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
@Named("searchParams") @ApplicationScoped
public class SearchParamsBean implements Serializable {
    @Getter private List<Fieldset> fields;
    
    @PostConstruct
    public void init() {
        initFields();
    }
    
    private void initFields() {
        fields = new ArrayList<>();
        fields.addAll(Arrays.asList(Fieldset.values()));
    }
}