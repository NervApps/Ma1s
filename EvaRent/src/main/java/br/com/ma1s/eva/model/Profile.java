/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "PROFILE")
@EqualsAndHashCode(of = "id")
public class Profile implements Serializable {
    
    @Id
    @Column(name = "PROFILE_ID")
    @Getter @Setter private Long id;
    
    @Column(name = "NAME", nullable = false)
    @Getter @Setter private String name;
}
