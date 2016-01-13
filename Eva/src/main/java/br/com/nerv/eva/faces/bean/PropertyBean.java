/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nerv.eva.faces.bean;

import br.com.nerv.eva.exception.BusinessException;
import br.com.nerv.eva.faces.stereotype.ViewBean;
import br.com.nerv.eva.faces.util.Messages;
import br.com.nerv.eva.faces.util.UploadFile;
import br.com.nerv.eva.model.Account;
import br.com.nerv.eva.model.Property;
import br.com.nerv.eva.model.Proprietor;
import br.com.nerv.eva.model.enums.Bank;
import br.com.nerv.eva.service.PropertyService;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import lombok.Getter;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 * Managed bean que controla as telas de cadastro de imóveis
 *
 * @author vitor
 */
@ViewBean
public class PropertyBean implements Serializable {

    @Getter
    private Property property;
    @Getter
    private List<String> images;
    @Inject
    private PropertyService service;
    @Inject
    private Messages msg;
    @Inject
    private ImageStreamBean streamer;
    private List<UploadFile> files;
    private static final OpenOption[] OPTIONS = {
        StandardOpenOption.WRITE,
        StandardOpenOption.CREATE,
        StandardOpenOption.TRUNCATE_EXISTING
    };

    @PostConstruct
    public void init() {
        files = new ArrayList();
        property = getFromContext();
        if (property == null) {
            final Proprietor p = new Proprietor();
            p.setAccount(new Account());

            property = new Property();
            property.setProprietor(p);
        } else {
            images = streamer.loadImages(property);
        }
    }

    public void save() {
        Property saved = service.newProperty(property);
        if (saved == null || saved.getId() == null) {
            throw new BusinessException("Erro ao inserir imóvel");
        } else {
            saveImages(saved);
            msg.info("Imóvel: 00" + saved.getId() + " cadastrado com sucesso");
            init();
        }
    }

    public void update() {
        Property saved = service.update(property);
        saveImages(saved);
        msg.info("Imóvel atualizado com sucesso");
    }

    private void saveImages(Property saved) {
        try {
            Path dir = streamer.createImagesFolder(saved);
            for (UploadFile file : files) {
                Path uploaded = Paths.get(dir.toString() + File.separator + file.getFileName());
                Files.write(uploaded, file.getContents(), OPTIONS);
            }

        } catch (IOException e) {
            throw new BusinessException("Erro ao gravar imagens", e);
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        files.add(new UploadFile(file.getFileName(), file.getContents()));
    }

    public List<Bank> getBanks() {
        return Arrays.asList(Bank.values());
    }

    private Property getFromContext() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Flash flash = ctx.getExternalContext().getFlash();
        Object get = flash.get("property");
        return get == null ? null : (Property) get;
    }
}
