/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.web.beans;

import br.com.ma1s.eva.model.Account;
import br.com.ma1s.eva.model.Property;
import br.com.ma1s.eva.model.Proprietor;
import br.com.ma1s.eva.service.PropertyService;
import br.com.ma1s.eva.web.beans.common.ConversationBean;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor
 */
@Named
public class PropertyBean extends ConversationBean implements Serializable {
    private static final String IMG_PATH = "/resources/property-img/";
    private static final String PARAM = "property";
    
    @Getter private List<Part> images;    
    @Getter private Property property;
    @Getter @Setter private Part file;
    
    @Inject private PropertyService service;
    
    @PostConstruct
    public void init() {
        final Property param = getParam(PARAM, Property.class);
        if (param != null)
            property = param;
        else {
            final Proprietor p = new Proprietor();
            p.setAccount(new Account());

            property = new Property();
            property.setProprietor(p);
        }
        
        images = new ArrayList<>();
    }
    
    @Override
    protected void initConversation() {
        createPropertyImgFolder();
        super.initConversation();
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
            return toStep(4, "cad_property_proprietor");
        }
        return null;
    }
    
    public String save() {
        try {
            final Property saved = service.newProperty(property);
            
            if (!images.isEmpty())
                saveImages(saved);
            
            endConversation();
            info("Imóvel inserido com sucesso");
            
            init();
            return toStep(1, "cad_property_info?faces-redirect=true");
        } catch (IOException e) {
            error("Erro ao inserir imóvel", e.getMessage());
        }
        return null;
    }
    
    private void saveImages(final Property saved) throws IOException {
        final String fileName = getPath() + IMG_PATH + saved.getId();
        final File folder = new File(fileName);
        folder.mkdir();
        
        for (Part img : images) {
            writePhoto(img.getInputStream(), fileName + "/" + img.getSubmittedFileName());
        }
    }
    
    private void writePhoto(final InputStream stream, final String fileName) 
                throws IOException {
        
        final File photo = new File(fileName);
        if (!photo.exists()) {
            photo.createNewFile();
        }
        
        Files.copy(stream, photo.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
    
    private void createPropertyImgFolder() {
        final File folder = new File(getPath() + IMG_PATH);
        
        try {
            if (!folder.exists())
                folder.mkdir();
            
            System.out.println("img property folder successfull created");
        } catch (Exception e) {
            System.err.println("Error to create img property folder");
            e.printStackTrace(System.err);
        }
    }
}
