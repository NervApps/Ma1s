/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nerv.eva.faces.bean;

import br.com.ma1s.eva.model.User;
import br.com.ma1s.eva.model.enums.ActiveStatus;
import br.com.nerv.eva.faces.stereotype.ViewBean;
import br.com.nerv.eva.faces.util.Messages;
import br.com.nerv.eva.service.UserService;
import java.io.Serializable;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;

/**
 * Bean que controla a tela de login
 * @author Vitor
 */
@ViewBean
public class LoginBean implements Serializable {
    @Inject private Messages messages;
    @Inject private UserLoggedBean bean;
    @Inject private UserService service;
    @Getter @Setter private String username;
    @Getter @Setter private String password;
    @Getter @Setter private String email;
    
    public String login() {
        User user = service.getUser(username, password);
        if (user == null) {
            messages.error("Usuário inexistente");
            return null;
        } else {
            bean.register(username);
            return "index?faces-redirect=true";
        }
    }
    
    public void newUser() {
        User user = new User();
        user.setLogin(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setStatus(ActiveStatus.ACTIVE);
        service.newUser(user);
        messages.info("Usuário criado com sucesso");
    }
}