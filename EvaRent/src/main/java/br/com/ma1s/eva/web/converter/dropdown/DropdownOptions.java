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
 * @param <T> O tipo do objeto que ser� exibido na combo box. Este objeto
 * deve possuir um m�todo getLabel() que � chamado na tela.
 */
public interface DropdownOptions<T> {
    
    List<T> getOptions();
    
    T convert(final Object value);
}
