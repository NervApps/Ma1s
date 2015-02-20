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
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor
 */
@Named @ConversationScoped
public class PropertyBean implements Serializable {
    private static final int SECOND_STEP = 2;
    private static final int THIRD_STEP = 3;
    
    @Getter private Property property;
    @Getter @Setter private Part file;
    @Getter private int step = 1;
    
    @Inject private PropertyService service;
    @Inject private Conversation conv;
    
    @PostConstruct
    public void init() {
        property = new Property();
    }
    
    private void initConversation() {
        final FacesContext ctx = FacesContext.getCurrentInstance();
        
        if (ctx.isPostback() && conv.isTransient())
            conv.begin();
    }
    
    public String fillProperty() {
        String page = null;
        Message msg;
        
        try {
            initConversation();
            property.buildPhoto(file.getInputStream());
            
            step = SECOND_STEP;
            page = "cad_property_img";
        } catch (IOException e) {
            msg = new Message(MessageType.ERROR, "Erro ao gravar imagem de fachada");
            msg.show();
        }
        return page;
    }
    
    public String addImage() {
        Message msg;
        String page = null;
        try {
            step = THIRD_STEP;
        } catch (Exception e) {
            msg = new Message(MessageType.ERROR, "Erro ao gravar imagens", e.getMessage());
            msg.show();
        }
        return page;
    }
    
    public void save() {
        Message msg;
        try {
            service.save(property);
            conv.end();
            msg = new Message(MessageType.INFO, "Imóvel inserido com sucesso");
        } catch (Exception e) {
            msg = new Message(MessageType.ERROR, "Erro ao inserir imóvel", e.getMessage());
        }
        msg.show();
    }
    
    public String toStep(final int step, final String page) {
        this.step = step;
        return page;
    }
}