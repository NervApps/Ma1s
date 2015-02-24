/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.model;

import br.com.ma1s.eva.model.enums.PropertyStatus;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
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
    
    @Column(name = "ADDRESS", nullable = false)
    @Getter @Setter private String address;
    
    @Column(name = "NUMBER", nullable = false)
    @Getter @Setter private String number;
    
    @Column(name = "COMPLEMENT")
    @Getter @Setter private String complement;
    
    @Column(name = "NEIGHBORHOOD", nullable = false)
    @Getter @Setter private String neighborhood;
    
    @Column(name = "AREA", nullable = false)
    @Getter @Setter private Integer area;
    
    @Column(name = "PROPERTY_VALUE", nullable = false)
    @Getter @Setter private BigDecimal value;
    
    @Column(name = "PROPERTY_RENT_VALUE", nullable = false)
    @Getter @Setter private BigDecimal rentValue;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    @Getter @Setter private PropertyStatus status;
    
    @Column(name = "PHOTO", nullable = false)
    @Getter @Setter private String photo;
    
    @Column(name = "KITCHEN")
    @Getter @Setter private Integer kitchen;
    
    @Column(name = "ROOM")
    @Getter @Setter private Integer room;
    
    @Column(name = "DINNER_ROOM")
    @Getter @Setter private Integer dinnerRoom;
    
    @Column(name = "BEDROOM")
    @Getter @Setter private Integer bedroom;
    
    @Column(name = "LAUNDRY")
    @Getter @Setter private Integer laundry;
    
    @Column(name = "BATHROOM")
    @Getter @Setter private Integer bathroom;
    
    @Column(name = "SUITE")
    @Getter @Setter private Integer suite;
    
    @Column(name = "PARKING_SPACES")
    @Getter @Setter private Integer parking;
    
    @ManyToOne
    @JoinColumn(name = "PROPRIETOR_ID", referencedColumnName = "PROPRIETOR_ID", 
                nullable = false)
    @Getter @Setter private Proprietor proprietor;
    
    @Transient
    @Getter private String fileExtension;
    
    @Transient
    @Getter @Setter private InputStream photoStream;

    public void setFileExtension(final String fileName) {
        this.fileExtension = fileName.substring(fileName.indexOf("."));
    }
}