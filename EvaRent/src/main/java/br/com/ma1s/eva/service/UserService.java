/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.service;

import br.com.ma1s.eva.exception.BusinessException;
import br.com.ma1s.eva.model.Profile;
import br.com.ma1s.eva.model.User;
import br.com.ma1s.eva.model.enums.ActiveStatus;
import br.com.ma1s.eva.model.repository.ProfileDAO;
import br.com.ma1s.eva.model.repository.UserDAO;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Vitor
 */
@RequestScoped
public class UserService {
    
    @Inject private UserDAO userDAO;
    @Inject private ProfileDAO profileDAO;
    
    public void newUser(final User user) {
        try {
            if (exists(user.getLogin()))
                throw new BusinessException("Usuário já existente");
            else {
                user.setStatus(ActiveStatus.ACTIVE);
                userDAO.saveAndFlush(user);
            }
        } catch (Exception e) {
            throw new BusinessException("Erro ao criar usuário", e);
        }
    }
    
    public User getUser(final String login, final String password) {
        final String encoded = DigestUtils.sha1Hex(password);
        final User get = userDAO.findByLoginEqualAndPasswordEqual(login, encoded);
        if (get != null)
            return get;
        else
            throw new BusinessException("Usuário não encontrado");
    }
    
    public boolean exists(final String login) {
        return userDAO.findByLoginEqual(login) != null;
    }
    
    public Profile newProfile(final Profile p) {
        final Profile found = profileDAO.findByNameEqual(p.getName());
        if (found == null)
            return profileDAO.save(p);
        else
            return found;
    }
}