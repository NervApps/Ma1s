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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor
 */
@Entity
@Table(name = "FIELD_TABLE")
public class FieldTable implements Serializable {
    
    @Id
    @Column(name = "FIELD_TABLE_ID")
    @Getter @Setter private Long id;
    
    @Column(name = "TABLE_NAME")
    @Getter @Setter private String tableName;
    
    @Column(name = "COLUMN_NAME")
    @Getter @Setter private String columnName;
    
    @ManyToOne
    @JoinColumn(name = "FIELD_ID", referencedColumnName = "FIELD_ID")
    @Getter @Setter private Field field;
}
