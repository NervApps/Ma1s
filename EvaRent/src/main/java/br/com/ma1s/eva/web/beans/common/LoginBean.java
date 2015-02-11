/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.web.beans.common;

import br.com.ma1s.eva.model.User;
import br.com.ma1s.eva.web.context.UserLogged;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor Ribeiro de Oliveira
 */
@Named @RequestScoped
public class LoginBean extends ManagedBean {    
    
    @Getter @Setter private String login;
    @Getter @Setter private String password;
    @Getter @Setter private String email;
    
    @Inject private UserLogged logged;
    
    @PostConstruct
    public void init() {
        login = new String();
        password = new String();
        email = new String();
    }
    
    public String doLogin() {
        logged.login(new User());
        return "index?faces-redirect=true";
    }
    
    public void create() {
        
    }
    
    public String doLogout() {
        logged.logout();
        return "login?faces-redirect=true";
    }
}