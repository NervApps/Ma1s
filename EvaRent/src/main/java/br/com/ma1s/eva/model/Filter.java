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
import lombok.ToString;

/**
 *
 * @author Vitor
 */
@Entity
@Table(name = "FILTER")
@EqualsAndHashCode
public class Filter implements Serializable {
    
    @Id
    @Column(name = "FIELD_ID")
    @Getter @Setter private Long id;
    
    @Column(name = "LABEL")
    @Getter @Setter private String label;
    
    @Column(name = "SQL_FILTER")
    @Getter @Setter private String sql;

    public Filter() {
    }

    public Filter(String label, String sql) {
        this.label = label;
        this.sql = sql;
    }
}