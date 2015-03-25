/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.web.context.config;

import br.com.ma1s.eva.model.Permission;
import br.com.ma1s.eva.model.Profile;
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
public class BlockedPagesConfig {
    
    @Inject private UserService service;
    
    @PostConstruct
    public void blockedPages() {
        final Profile profile = Profile.buildAdmin();
        service.addPermission(buildPermission("VIEW_USR", "/user_config"), profile);
        service.addPermission(buildPermission("EDIT_USR_PROFILE", "/user_profile_edt"), profile);
    }
    
    private Permission buildPermission(String name, String page) {
        final Permission p = new Permission();
        p.setName(name);
        p.setToView(page);
        return p;
    }
}
