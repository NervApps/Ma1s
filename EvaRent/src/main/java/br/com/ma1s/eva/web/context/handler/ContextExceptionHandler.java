/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.web.context.handler;

import br.com.ma1s.eva.web.util.Message;
import br.com.ma1s.eva.web.util.MessageType;
import javax.enterprise.event.Observes;

/**
 *
 * @author Vitor
 */
public abstract class ContextExceptionHandler {
    
    public void handle(@Observes Throwable cause) {
        while(cause != null && !type().isInstance(cause)) {
            cause = cause.getCause();
        }
        
        if (cause != null) {
            final String title = getTranslateMessage(cause);
            final Message msg = new Message(MessageType.ERROR, title);
            msg.show();
        }
    }
    
    protected abstract Class<? extends Throwable> type();
    
    protected String getTranslateMessage(final Throwable exception) {
        return exception.getMessage();
    }
}
