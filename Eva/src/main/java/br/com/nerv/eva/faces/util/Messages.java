/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nerv.eva.faces.util;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Classe que encapsula o envio de mensagens para o contexto JSF.
 * @author Vitor
 */
@RequestScoped
public class Messages {
    
    public void info(String message) {
        addMessage(FacesMessage.SEVERITY_INFO, message);
    }
    
    public void warn(String message) {
        addMessage(FacesMessage.SEVERITY_WARN, message);
    }
    
    public void error(String message) {
        addMessage(FacesMessage.SEVERITY_ERROR, message);
    }
    
    public void error(String message, Throwable e) {
        e.printStackTrace(System.err);
        addMessage(FacesMessage.SEVERITY_ERROR, message, e.getMessage());
    }
    
    private void addMessage(FacesMessage.Severity severity, String message) {
        addMessage(severity, message, null);
    }
    
    private void addMessage(FacesMessage.Severity severity, String message,
            String detail) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.addMessage(null, buildMessage(severity, message, detail));
    }
    
    private FacesMessage buildMessage(FacesMessage.Severity severiy, String descr,
            String detail) {
        return new FacesMessage(severiy, descr, detail);
    }
}