/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.web.beans;

import br.com.ma1s.eva.model.Profile;
import br.com.ma1s.eva.model.User;
import br.com.ma1s.eva.service.UserService;
import br.com.ma1s.eva.web.beans.common.ManagedBean;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor
 */
@Named @ViewScoped
public class UsrProfileBean extends ManagedBean implements Serializable {
    private static final String PARAM_USER = "user";
    private static final String PAGE_USERS = "user_config";
    @Getter private User user;
    @Getter @Setter private long profile;
    
    @Inject private UserService service;
    
    @PostConstruct
    public void init() {
        user = getParam(PARAM_USER, User.class);
        if (user == null) {
            error("Por favor, escolha um usuário");
            toPage(PAGE_USERS, true);
        }
    }
    
    public List<Profile> getProfiles() {
        return service.getProfiles();
    }
    
    public void update() {
        service.updateProfile(user, profile);
        info("Usuário: " + user.getLogin() + " atualizado com sucesso");
        toPage(PAGE_USERS, true);
    }
}