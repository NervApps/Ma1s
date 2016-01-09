package br.com.nerv.eva.service;

import br.com.ma1s.eva.model.Permission;
import br.com.ma1s.eva.model.Profile;
import br.com.ma1s.eva.model.User;
import br.com.ma1s.eva.model.enums.ActiveStatus;
import br.com.nerv.eva.exception.BusinessException;
import br.com.nerv.eva.model.repository.PermissionDAO;
import br.com.nerv.eva.model.repository.ProfileDAO;
import br.com.nerv.eva.model.repository.UserDAO;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

/**
 *
 * @author Vitor
 */
@RequestScoped
public class UserService {
    
    @Inject private UserDAO userDAO;
    @Inject private ProfileDAO profileDAO;
    @Inject private PermissionDAO permissionDAO;
    
    @Transactional
    public void newUser(final User user) {
        if (exists(user.getLogin()))
            throw new BusinessException("Usuário já existente");
        else {
            try {
                user.setStatus(ActiveStatus.ACTIVE);
                userDAO.save(user);
            } catch (Exception e) {
                throw new BusinessException("Erro ao criar usuário", e);
            }
        }
    }
    @Transactional
    public void update(final User user) {
        try {
            userDAO.save(user);
        } catch (Exception e) {
            throw new BusinessException("Erro ao atualizar usuário", e);
        }
    }
    @Transactional
    public void updateProfile(final User user, final long profileId) {
        try {
            user.setProfile(getProfile(profileId));
            update(user);
        } catch (Exception e) {
            throw new BusinessException("Erro ao atualizar usuário", e);
        }
    }
    
    public User getUser(final String login, final String password) {
        return userDAO.findByLoginEqualAndPasswordEqual(login, password);
    }
    
    public boolean exists(final String login) {
        return userDAO.findByLoginEqual(login) != null;
    }
    
    @Transactional
    public Profile newProfile(final Profile p) {
        final Profile found = getProfile(p.getName());
        if (found == null)
            return profileDAO.save(p);
        else
            return found;
    }
    
    @Transactional
    public void addPermission(Permission permission, Profile profile) {
        final Profile base = getProfile(profile.getName());
        if (base == null)
            throw new BusinessException("Perfil inválido para associação");
        
        permission = newPermission(permission);
        
        final List<Permission> permissions = profileDAO.getPermissions(profile.getId());
        if (!permissions.contains(permission)) {
            profile.addPermission(newPermission(permission));
            profileDAO.save(base);
        }
    }
    
    @Transactional
    public Permission newPermission(Permission permission) {
        final Permission found = permissionDAO.findByNameEqual(permission.getName());
        if (found != null)
            return found;
        else
            return permissionDAO.save(permission);
    }
    
    public Profile getProfile(String name) {
        return profileDAO.findByNameEqual(name);
    }
    
    public Profile getProfile(final long id) {
        return profileDAO.findBy(id);
    }
    
    public List<Profile> getProfiles() {
        return profileDAO.findAll();
    }
    
    public List<User> getUsers() {
        return userDAO.findAll();
    }
    
    @Transactional
    public void remove(final User user) {
        try {
            userDAO.remove(user);
        } catch (Exception e) {
            throw new BusinessException("Erro ao remover o usuário", e);
        }
    }
}