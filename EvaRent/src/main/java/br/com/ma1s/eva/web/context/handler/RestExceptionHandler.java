/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.web.context.handler;

import br.com.ma1s.eva.web.messages.rest.MessagesTranslator;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Vitor
 */
public class RestExceptionHandler extends ContextExceptionHandler {
    
    @Inject private MessagesTranslator translator;

    @Override
    protected Class<WebApplicationException> type() {
        return WebApplicationException.class;
    }

    @Override
    protected String getTranslateMessage(Throwable ex) {
        final WebApplicationException e = (WebApplicationException) ex;
        return translator.translate(e);
    }
}