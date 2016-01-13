/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nerv.eva.model.repository;

import br.com.nerv.eva.model.Property;
import br.com.nerv.eva.model.Property_;
import br.com.nerv.eva.model.enums.PropertyStatus;
import br.com.nerv.eva.model.enums.PropertyType;
import java.util.List;
import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.criteria.Criteria;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;

/**
 *
 * @author Vitor
 */
@Repository
public abstract class PropertyDAO extends AbstractEntityRepository<Property, Long> 
                                  implements CriteriaSupport<Property> {
    
    @Query(value = "FROM Property p WHERE p.address = ?1 AND p.number = ?2 "
                 + "AND p.complement = ?3 AND p.status = ?4")
    public abstract List<Property> find(String address, String number, String complement, PropertyStatus status);
    
    @Query(value = "FROM Property p WHERE p.address = ?1 AND p.number = ?2 "
                 + "AND p.status = ?3")
    public abstract List<Property> find(String address, String number, PropertyStatus status);
    
    public List<Property> find(Property property) {
        Criteria<Property, Property> query = criteria();
        
        Long id = property.getId();
        if (id != null && id > 0)
            query.eq(Property_.id, id);
        
        String neighborhood = property.getNeighborhood();
        if (neighborhood != null && !neighborhood.isEmpty())
            query.like(Property_.neighborhood, neighborhood + "%");
        
        PropertyStatus status = property.getStatus();
        if (status != null)
            query.eq(Property_.status, status);
        
        PropertyType type = property.getType();
        if (type != null)
            query.eq(Property_.type, type);
        
        return query.orderAsc(Property_.type)
                    .orderAsc(Property_.neighborhood)
                    .getResultList();
    }
}