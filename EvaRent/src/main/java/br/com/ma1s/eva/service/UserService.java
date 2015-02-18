/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.service;

import br.com.ma1s.eva.exception.BusinessException;
import br.com.ma1s.eva.model.User;
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
    
    @Inject private UserDAO dao;
    
    public void newUser(final User user) {
        try {
            if (exists(user.getLogin()))
                throw new BusinessException("Usu�rio j� existente");
            else
                dao.saveAndFlush(user);
        } catch (Exception e) {
            throw new BusinessException("Erro ao criar usu�rio", e);
        }
    }
    
    public User getUser(final String login, final String password) {
        final String encoded = DigestUtils.sha1Hex(password);
        final User get = dao.findByLoginEqualdAndPasswordEqual(login, encoded);
        if (get != null)
            return get;
        else
            throw new BusinessException("Usu�rio n�o encontrado encontrado");
    }
    
    public boolean exists(final String login) {
        return dao.findBy(login) != null;
    }
}