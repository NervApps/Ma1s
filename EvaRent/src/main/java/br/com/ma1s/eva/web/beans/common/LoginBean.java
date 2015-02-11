/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.web.beans.common;

import br.com.ma1s.eva.model.User;
import br.com.ma1s.eva.service.UserService;
import br.com.ma1s.eva.web.context.UserLogged;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor
 */
@Named @RequestScoped
public class LoginBean extends ManagedBean {    
    
    @Getter @Setter private String login;
    @Getter @Setter private String password;
    @Getter @Setter private String email;
    
    @Inject private UserLogged logged;
    @Inject private UserService service;
    
    @PostConstruct
    public void init() {
        login = new String();
        password = new String();
        email = new String();
    }
    
    public String doLogin() {
        final User user = service.getUser(login, password);
        if (!user.isActive())
            warn("Usuário inativo", "Entre em contato com o administrador");
        else {
            logged.login(user);
            return "index?faces-redirect=true";
        }
        
        return null;
    }
    
    public void create() {
        final User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        
        service.newUser(user);
        info("Usuário criado com sucesso");
    }
    
    public String doLogout() {
        logged.logout();
        return "login?faces-redirect=true";
    }
}