/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.web.beans.common;

import br.com.ma1s.eva.web.util.Message;
import br.com.ma1s.eva.web.util.MessageType;
import javax.faces.application.Application;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.ServletContext;

/**
 *
 * @author Vitor
 */
public abstract class ManagedBean {
    protected static final String PARAM_FROM_PAGE = "fromPage";
    
    protected void info(final String title, final String detail) {
        final Message msg = new Message(MessageType.INFO, title, detail);
        msg.show();
    }
    
    protected void info(final String title) {
        final Message msg = new Message(MessageType.INFO, title);
        msg.show();
    }
    
    protected void warn(final String title, final String detail) {
        final Message msg = new Message(MessageType.WARN, title, detail);
        msg.show();
    }
    
    protected void warn(final String title) {
        final Message msg = new Message(MessageType.WARN, title);
        msg.show();
    }
    
    protected void error(final String title, final String detail) {
        final Message msg = new Message(MessageType.ERROR, title, detail);
        msg.show();
    }
    
    protected void error(final String title) {
        final Message msg = new Message(MessageType.ERROR, title);
        msg.show();
    }
    
    protected void putParam(final String name, final Object value) {
        getFlash().put(name, value);
    }
    
    protected <T> T getParam(final String name, final Class<T> type) {
        return (T) getFlash().get(name);
    }
    
    protected String getPath() {
        final ServletContext sc = (ServletContext) getExternalContext().getContext();
        return sc.getRealPath("");
    }
    
    protected void toPage(String page, boolean redirect) {
        final String toPage = redirect ? page.concat("?faces-redirect=true")
                                       : page;
        
        final FacesContext ctx = FacesContext.getCurrentInstance();
        final Application app = ctx.getApplication();
        app.getNavigationHandler().handleNavigation(ctx, null, toPage);
    }
    
    private Flash getFlash() {
        final ExternalContext ext = getExternalContext();
        return ext.getFlash();
    }
    
    private ExternalContext getExternalContext() {
        final FacesContext ctx = FacesContext.getCurrentInstance();
        return ctx.getExternalContext();
    }
}