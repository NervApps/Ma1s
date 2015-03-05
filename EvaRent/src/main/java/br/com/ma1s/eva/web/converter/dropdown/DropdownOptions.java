/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.web.converter.dropdown;

import java.util.List;

/**
 *
 * @author Vitor
 */
public interface DropdownOptions<T> {
    
    List<T> getOptions();
    
    T convert(final Object value);
}
