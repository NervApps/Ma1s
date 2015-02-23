/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.web.beans;

import br.com.ma1s.eva.model.Property;
import br.com.ma1s.eva.service.PropertyService;
import br.com.ma1s.eva.web.beans.common.ManagedBean;
import br.com.ma1s.eva.web.util.Message;
import br.com.ma1s.eva.web.util.MessageType;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor
 */
@Named @ConversationScoped
public class PropertyBean extends ManagedBean implements Serializable {
    private static final String IMG_PATH = "/resources/property-img/";
    
    @Getter private List<Part> images;    
    @Getter private Property property;
    @Getter @Setter private Part file;
    @Getter private int step = 1;
    
    @Inject private PropertyService service;
    @Inject private Conversation conv;
    
    @PostConstruct
    public void init() {
        property = new Property();
        images = new ArrayList<>();
    }
    
    private void initConversation() {
        final FacesContext ctx = FacesContext.getCurrentInstance();
        
        if (ctx.isPostback() && conv.isTransient())
            conv.begin();
    }
    
    public String fillProperty() {
        try {
            initConversation();
            if (service.exists(property))
                warn("Imóvel já cadastrado");
            else
                return toStep(2, "cad_property_internal");
        } catch (Exception e) {
            error("Erro ao processar o cadastro", e.getMessage());
        }
        return null;
    }
    
    public void addImage() {
        if (images.contains(file))
            warn("Imagem já adicionada");
        else
            images.add(file);
    }
    
    public void removeImage() {
        images.remove(file);
    }
    
    public String saveImages() {
        if (!images.isEmpty()) {
            try {
                final Part first = images.get(0);
                property.setFileExtension(first.getSubmittedFileName());
                property.setPhotoStream(first.getInputStream());
                
                return toStep(4, "cad_property_info");
            } catch (IOException e) {
                error("Erro ao processar imagens", e.getMessage());
            }
        }
        return null;
    }
    
    public void save() {
        Message msg;
        try {
            final Property saved = service.save(property);
            final String fileName = IMG_PATH + saved.getId() + property.getFileExtension();
            saved.setPhoto(fileName);
            service.update(saved);
        
            writePhoto(property.getPhotoStream(), getPath() + fileName);
            
            if (!images.isEmpty())
                saveOtherImages(property);
            
            conv.end();
            msg = new Message(MessageType.INFO, "Imóvel inserido com sucesso");
        } catch (Exception e) {
            msg = new Message(MessageType.ERROR, "Erro ao inserir imóvel", e.getMessage());
        }
        msg.show();
    }
    
    private void saveOtherImages(final Property saved) throws IOException {
        final String fileName = getPath() + IMG_PATH + saved.getId();
        final File folder = new File(fileName);
        folder.createNewFile();
        
        for (Part img : images) {
            writePhoto(img.getInputStream(), fileName + "/" + img.getSubmittedFileName());
        }
    }
    
    public String toStep(final int step, final String page) {
        this.step = step;
        return page;
    }
    
    private void writePhoto(final InputStream stream, final String fileName) 
                throws IOException {
        
        final File photo = new File(fileName);
        Files.copy(stream, photo.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
    
    private String getPath() {
        final FacesContext ctx = FacesContext.getCurrentInstance();
        final ServletContext sc = (ServletContext) ctx.getExternalContext().getContext();
        return sc.getRealPath("");
    }
}
