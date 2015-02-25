/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.model.repository.query;

import br.com.ma1s.eva.web.beans.enums.Fieldset;

/**
 *
 * @author Vitor
 * @param <T> A entidade tratada na busca
 */
public interface SearchQueryTranslator<T> {
    
    /**
     * Retorna o nome do atributo da entidade
     * correspondente.
     * @param field O nome do campo que foi usado
     * na busca.
     * @return O nome do atributo correspondente
     * na entidade.
     */
    String getAttribute(final Fieldset field);
    
    /**
     * Define o tipo da entidade que será usada na query.
     * @return A classe da entidade.
     */
    Class<T> getType();
}
