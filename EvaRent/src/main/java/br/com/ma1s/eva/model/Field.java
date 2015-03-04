/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor
 */
@Entity
@Table(name = "FIELD")
@EqualsAndHashCode
public class Field implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FIELD_ID")
    @Getter @Setter private Long id;
    
    @Column(name = "LABEL")
    @Getter @Setter private String label;
    
    @Column(name = "PLACEHOLDER")
    @Getter @Setter private String placeholder;
    
    @Column(name = "INPUT_TYPE")
    @Getter @Setter private String inputType;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "FIELD_FILTER",
               joinColumns = {@JoinColumn(name = "FILTER_ID", nullable = false)},
               inverseJoinColumns = {@JoinColumn(name = "FIELD_ID", nullable = false)})
    @Getter @Setter private List<Filter> filters;

    public Field() {}

    public Field(String label, String placeholder, String inputType, List<Filter> filters) {
        this.label = label;
        this.placeholder = placeholder;
        this.inputType = inputType;
        this.filters = filters;
    }
}