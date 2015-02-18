/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.web.beans;

import br.com.ma1s.eva.model.Property;
import br.com.ma1s.eva.service.PropertyService;
import br.com.ma1s.eva.web.util.Message;
import br.com.ma1s.eva.web.util.MessageType;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor
 */
@Named @ViewScoped
public class PropertyBean implements Serializable {
    @Getter private Property property;
    @Getter @Setter private Part file;
    
    @Inject private PropertyService service;
    
    @PostConstruct
    public void init() {
        property = new Property();
    }
    
    public void save() {
        Message msg;
        try {
            if (file != null)
                service.save(property, file.getInputStream());
            else
                service.save(property);
            
            msg = new Message(MessageType.INFO, "Imóvel inserido com sucesso");
        } catch (IOException e) {
            msg = new Message(MessageType.ERROR, "Erro ao gravar foto", e.getMessage());
        }
        msg.show();
    }
}
