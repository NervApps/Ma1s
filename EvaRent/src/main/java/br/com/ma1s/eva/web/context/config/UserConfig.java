/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.web.context.config;

import br.com.ma1s.eva.exception.BusinessException;
import br.com.ma1s.eva.model.Profile;
import br.com.ma1s.eva.model.User;
import br.com.ma1s.eva.model.enums.ActiveStatus;
import br.com.ma1s.eva.service.UserService;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author Vitor
 */
@Startup @Singleton
public class UserConfig {
    
    @Inject private UserService usrService;
    
    @PostConstruct
    public void createAdminUser() {
        try {
            final Profile p = Profile.buildAdmin();
            final User usr = getUser();
            usr.setProfile(usrService.newProfile(p));
            
            if (!usrService.exists(usr.getLogin()))
                usrService.newUser(usr);
        } catch (BusinessException e) {
            System.out.println("Erro ao criar admin");
            e.printStackTrace(System.err);
        }
    }
    
    private User getUser() {
        final User user = new User();
        user.setLogin("admin");
        user.setPassword("qwe123!@#");
        user.setEmail("admin@nerv.com.br");
        user.setStatus(ActiveStatus.ACTIVE);
        return user;
    }
}
