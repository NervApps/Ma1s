/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nerv.eva.faces.exception;

import java.util.Iterator;
import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ExceptionQueuedEvent;

/**
 * Handler de exceçoes lançadas pelo container JSF.
 * @author Vitor
 */
class CustomExceptionHandler extends ExceptionHandlerWrapper {
    
    private final ExceptionHandler wrapped;

    public CustomExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }

    @Override
    public void handle() throws FacesException {
        Iterable<ExceptionQueuedEvent> ex = getUnhandledExceptionQueuedEvents();
        Iterator<ExceptionQueuedEvent> it = ex.iterator();
        
        if (it.hasNext()) {
            Throwable error = getCause(it.next().getContext().getException());
            toErrorPage(error);
        }
        getWrapped().handle();
    }
    /**
     * Redireciona para a página de erros da aplicação.
     */
    private void toErrorPage(Throwable e) {
        System.out.println("Erro: " + e);
        putMessage(e);
        
        FacesContext ctx = FacesContext.getCurrentInstance();
        NavigationHandler nav = ctx.getApplication().getNavigationHandler();
        nav.handleNavigation(ctx, null, "error?faces-redirect=true");
        ctx.responseComplete();
    }
    /**
     * Inclui e mensagem de erro no contexto para ser exibida pela página.
     * @param e A exceção que ocorreu.
     */
    private void putMessage(Throwable e) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Flash flash = ctx.getExternalContext().getFlash();
        flash.put("cause", e.getMessage());
    }
    /**
     * Obtém a causa raíz do erro capturado pelo contexto.
     * @param error A exceção obtida pelo contexto jsf.
     * @return A causa da exceção
     */
    private Throwable getCause(Throwable error) {
        if (error.getCause() != null) {
            while (error.getCause() != null) {
                error = error.getCause();
            }
        }
        return error;
    }
}