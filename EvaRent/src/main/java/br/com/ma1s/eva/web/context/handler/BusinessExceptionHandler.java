/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.web.context.handler;

import br.com.ma1s.eva.exception.BusinessException;

/**
 *
 * @author Vitor
 */
public class BusinessExceptionHandler extends ContextExceptionHandler {

    @Override
    protected Class<BusinessException> type() {
        return BusinessException.class;
    }
}