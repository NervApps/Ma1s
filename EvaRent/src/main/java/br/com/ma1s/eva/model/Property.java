/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.model;

import br.com.ma1s.eva.model.enums.PropertyStatus;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor
 */
@Entity
@Table(name = "PROPERTY")
@EqualsAndHashCode(of = "id")
public class Property implements Serializable {
    
    @Id
    @Column(name = "PROPERTY_ID")
    @Getter @Setter private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    @Getter @Setter private PropertyStatus status;
}
