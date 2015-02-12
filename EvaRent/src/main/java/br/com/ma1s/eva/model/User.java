/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.model;

import br.com.ma1s.eva.model.enums.ActiveStatus;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Vitor
 */
@Entity
@Table(name = "USER")
@EqualsAndHashCode(of = "id")
public class User implements Serializable {
    
    @Id
    @Column(name = "USER_ID")
    @Getter @Setter private Long id;
    
    @Column(name = "LOGIN", nullable = false) 
    @Getter @Setter private String login;
    
    @Column(name = "PASSWORD", nullable = false)
    @Getter private String password;
    
    @Column(name = "EMAIL", nullable = false)
    @Getter @Setter private String email;
    
    @ManyToOne
    @JoinColumn(name = "PROFILE_ID", referencedColumnName = "PROFILE_ID")
    @Getter @Setter private Profile profile;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "ACTIVE")
    @Getter @Setter private ActiveStatus status;

    public User() {}
    
    public User(final String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = DigestUtils.sha1Hex(password);
    }
    
    public boolean isActive() {
        return ActiveStatus.ACTIVE.equals(status);
    }
}