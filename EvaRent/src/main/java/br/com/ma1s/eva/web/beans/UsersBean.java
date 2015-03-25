/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.web.beans;

import br.com.ma1s.eva.model.User;
import br.com.ma1s.eva.model.enums.ActiveStatus;
import br.com.ma1s.eva.service.UserService;
import br.com.ma1s.eva.web.beans.common.ManagedBean;
import java.io.Serializable;
import java.util.List;
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
public class UsersBean extends ManagedBean implements Serializable {
    private final String userParam = "user";
    @Getter @Setter private User selected;
    @Inject private UserService service;
    
    public List<User> getAllUsers() {
        return service.getUsers();
    }
    
    public void updateProfile() {
        putParam(userParam, selected);
        toPage("user_profile_edt", true);
    }
    
    public void lock() {
        selected.setStatus(ActiveStatus.INACTIVE);
        service.update(selected);
        info("Usuário: " + selected.getLogin() + " bloqueado com sucesso");
    }
    
    public void unlock() {
        selected.setStatus(ActiveStatus.ACTIVE);
        service.update(selected);
        info("Usuário: " + selected.getLogin() + " desbloqueado com sucesso");
    }
    
    public void remove() {
        service.remove(selected);
        info("Usuário: " + selected.getLogin() + " removido com sucesso");
    }
}