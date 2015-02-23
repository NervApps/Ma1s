/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.model.repository;

import br.com.ma1s.eva.model.Permission;
import java.util.List;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

/**
 *
 * @author Vitor
 */
@Repository
public interface PermissionDAO extends EntityRepository<Permission, Long> {
    
    @Query(value = "SELECT DISTINCT p.page FROM Permission p")
    public List<String> getProtectedPages();
}