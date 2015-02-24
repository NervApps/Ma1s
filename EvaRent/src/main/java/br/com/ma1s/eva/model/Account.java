/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.model;

import br.com.ma1s.eva.model.enums.Bank;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor
 */
@Entity
@Table(name = "ACCOUNT")
public class Account implements Serializable {
    
    @Id
    @Column(name = "ACCOUNT_ID")
    @Getter @Setter private Long id;
    
    @Column(name = "AGENCY")
    @Getter @Setter private String agency;
    
    @Column(name = "NUMBER")
    @Getter @Setter private String number;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "BANK")
    @Getter @Setter private Bank bank;
}
