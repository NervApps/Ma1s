/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nerv.eva.rent.bean;

import br.com.nerv.eva.core.web.stereotypes.Model;
import com.nerv.eva.core.annotations.Audited;
import com.nerv.eva.core.cached.CachedUser;
import javax.inject.Inject;

/**
 *
 * @author Mais
 */
@Model
public class LoginBean {
    
    @Inject private CachedUser cached;
    
    @Audited
    public String doLogin() {
        return "index?faces-redirect=true";
    }
}
