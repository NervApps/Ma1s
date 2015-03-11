/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.web.context;

import br.com.ma1s.eva.model.Permission;
import br.com.ma1s.eva.model.Profile;
import br.com.ma1s.eva.model.User;
import br.com.ma1s.eva.model.repository.ProfileDAO;
import br.com.ma1s.eva.web.qualifiers.LoggedIn;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Vitor
 */
@SessionScoped
public class UserLogged implements Serializable {
    
    @Inject private ProfileDAO dao;
    private User user;
    private List<Permission> permissions;
    
    @Produces @LoggedIn @Named("logged")
    public User getUser() {
        return user;
    }
    
    public void login(final User user) {
        this.user = user;
        loadPermissions();
    }
    
    public void logout() {
        this.user = null;
        invalidate();
    }
    
    public boolean isActive() {
        return getUser() != null;
    }
    
    public boolean isInactive() {
        return !isActive();
    }
    
    public boolean hasPermission(final String name) {
        if (permissions != null) {
            for (Permission p : permissions) {
                if (name.equals(p.getName()))
                    return true;
            }
        }
        return false;
    }
    
    public boolean hasPagePermission(final String page) {
        if (permissions != null) {
            for (Permission p : permissions) {
                if (page.equals(p.getToView()))
                    return true;
            }
        }
        return false;
    }
    
    private void loadPermissions() {
        final Profile p = user.getProfile();        
        if (p != null)
            permissions = dao.getPermissions(p.getId());
    }
    
    private void invalidate() {
        final FacesContext ctx = FacesContext.getCurrentInstance();
        final ExternalContext ext = ctx.getExternalContext();
        final HttpSession session = (HttpSession) ext.getSession(false);
        session.invalidate();
    }
    
    @PreDestroy
    public void dispose() {
        this.user = null;
    }
}