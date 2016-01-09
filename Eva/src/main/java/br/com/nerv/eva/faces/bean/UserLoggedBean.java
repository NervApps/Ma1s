/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nerv.eva.faces.bean;

import br.com.nerv.eva.faces.stereotype.SessionBean;
import br.com.nerv.eva.qualifier.UserLogged;
import java.io.Serializable;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

/**
 * Bean que guarda o usuário logado na aplicação.
 * @author Vitor
 */
@SessionBean
public class UserLoggedBean implements Serializable {
    
    private String user;

    @Produces @UserLogged
    public String getUser() {
        return user;
    }
    
    public void register(String user) {
        this.user = user;
    }
    
    public String logout() {
        this.user = null;
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().invalidateSession();
        return "login?faces-redirect=true";
    }
}
