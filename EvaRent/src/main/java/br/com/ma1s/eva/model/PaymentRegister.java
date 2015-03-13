/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.model;

import br.com.ma1s.eva.model.enums.PaymentStatus;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor
 */
@Entity
@Table(name = "PAYMENT_REGISTER")
public class PaymentRegister implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PAYMENT_REGISTER_ID")
    @Getter @Setter private Long id;
    
    @ManyToOne
    @JoinColumn(name = "PROPERTY_CUSTOMER_ID", 
                referencedColumnName = "PROPERTY_CUSTOMER_ID", nullable = false)
    @Getter @Setter private PropertyCustomer propertyCustomer;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "PAYMENT_DATE", nullable = false)
    @Getter @Setter private Date date;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "PAYMENT_STATUS", nullable = false)
    @Getter @Setter private PaymentStatus status;
    
    @Column(name = "PAYMENT_VALUE", nullable = false)
    @Getter @Setter private BigDecimal value;
}
