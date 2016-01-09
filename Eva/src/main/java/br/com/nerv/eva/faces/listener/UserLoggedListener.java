/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nerv.eva.faces.listener;

import br.com.nerv.eva.qualifier.UserLogged;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.event.Observes;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.inject.Inject;
import org.apache.deltaspike.jsf.api.listener.phase.AfterPhase;
import org.apache.deltaspike.jsf.api.listener.phase.JsfPhaseId;
import org.apache.deltaspike.jsf.api.listener.phase.JsfPhaseListener;

/**
 * Listener que verifica se o usuário está logado na aplicação.
 * @author Vitor
 */
@JsfPhaseListener
public class UserLoggedListener {
    
    @Inject @UserLogged private String user;
    
    private final List<String> nonVerify = Arrays.asList(
            "login.xhtml", "error.xhtml");
    
    public void listen(@Observes @AfterPhase(JsfPhaseId.RESTORE_VIEW) PhaseEvent event) {
        FacesContext ctx = event.getFacesContext();
        String page = ctx.getViewRoot().getViewId().replace("/", "");
        
        if (!nonVerify.contains(page) && user == null) {
            NavigationHandler nav = ctx.getApplication().getNavigationHandler();
            nav.handleNavigation(ctx, null, nonVerify.get(0) + "?faces-redirect=true");
        }
    }
}
