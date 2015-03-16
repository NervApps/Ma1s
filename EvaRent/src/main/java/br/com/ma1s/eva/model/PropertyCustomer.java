/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor
 */
@Entity
@Table(name = "PROPERTY_CUSTOMER")
public class PropertyCustomer implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PROPERTY_CUSTOMER_ID")
    @Getter @Setter private Long id;
    
    @ManyToOne
    @JoinColumn(name = "PROPERTY_ID", referencedColumnName = "PROPERTY_ID")
    @Getter @Setter private Property property;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "CUSTOMER_ID")
    @Getter @Setter private Customer customer;
    
    @Column(name = "PAYMENT_DAY", nullable = false)
    @Getter @Setter private Integer paymentDay;
    
    @Column(name = "CONTRACT_PERIOD", nullable = false)
    @Getter @Setter private Integer contractPeriod;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "STARTED_DATE", nullable = false)
    @Getter @Setter private Date startedDate;
    
    @Column(name = "DEPOSIT_VALUE")
    @Getter @Setter private BigDecimal depositValue;
    
    @Transient
    @Getter @Setter private Integer months = 3;
}