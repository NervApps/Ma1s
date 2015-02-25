/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.model.repository.query;

import br.com.ma1s.eva.model.Property;
import br.com.ma1s.eva.web.beans.enums.Fieldset;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Vitor
 */
@RequestScoped
public class PropertyQueryTranslator implements SearchQueryTranslator<Property> {
    private final Map<Fieldset, String> translate;

    public PropertyQueryTranslator() {
        translate = new HashMap<>();
        translate.put(Fieldset.BAIRRO, "neighborhood");
        translate.put(Fieldset.AREA_CONSTRUIDA, "area");
        translate.put(Fieldset.COZINHA, "kitchen");
    }

    @Override
    public String getAttribute(Fieldset field) {
        return translate.get(field);
    }

    @Override
    public Class<Property> getType() {
        return Property.class;
    }
}
