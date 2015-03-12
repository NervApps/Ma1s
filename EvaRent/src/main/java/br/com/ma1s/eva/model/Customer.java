/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor
 */
@Entity
@Table(name = "CUSTOMER")
public class Customer implements Serializable {
    
    @Id
    @Column(name = "CUSTOMER_ID")
    @Getter @Setter private Long id;
    
    @Column(name = "NAME", nullable = false)
    @Getter @Setter private String name;
    
    @Column(name = "DOCUMENT", nullable = false)
    @Getter @Setter private String document;
    
    @Column(name = "PHONE")
    @Getter @Setter private String phone;
    
    @Column(name = "CELLPHONE")
    @Getter @Setter private String cellphone;
    
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ACCOUNT_ID")
    @Getter @Setter private Account account;
}