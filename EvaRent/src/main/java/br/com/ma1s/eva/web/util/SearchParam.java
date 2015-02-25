/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.web.util;

import br.com.ma1s.eva.web.beans.enums.Fieldset;
import br.com.ma1s.eva.web.beans.enums.QueryConditions;
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
    @Getter @Setter private Fieldset field;
    @Getter @Setter private QueryConditions condition;
    @Getter @Setter private Object value;
    @Getter @Setter private Object value2;
}
