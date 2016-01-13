/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nerv.eva.faces.bean;

import br.com.nerv.eva.exception.BusinessException;
import br.com.nerv.eva.faces.stereotype.ApplicationBean;
import br.com.nerv.eva.model.Property;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 * Managed bean que controla a inserção e apresentação das imagens.
 * @author vitor
 */
@ApplicationBean
public class ImageStreamBean implements Serializable {

    public StreamedContent getImage() throws IOException {
        FacesContext ctx = FacesContext.getCurrentInstance();
        if (ctx.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            Map<String, String> params = ctx.getExternalContext().getRequestParameterMap();
            String id = params.get("id");
            File image = Paths.get(id).toFile();
            return new DefaultStreamedContent(new FileInputStream(image));
        }
    }

    public void removeImages(Property property) throws IOException {
        Path dir = createImagesFolder(property);
        DirectoryStream<Path> stream = Files.newDirectoryStream(dir);
        for (Path dirFile : stream) {
            Files.deleteIfExists(dirFile);
        }
    }

    public Path createImagesFolder(Property property) throws IOException {
        Path dir = Paths.get(File.separator + "images" + File.separator + property.getId());
        try {
            return Files.createDirectories(dir);
        } catch (FileAlreadyExistsException e) {
            return dir;
        }
    }
    
    public List<String> loadImages(Property property) {
        List<String> images = new ArrayList();
        try {
            Path dir = createImagesFolder(property);
            DirectoryStream<Path> stream = Files.newDirectoryStream(dir);
            for (Path dirFile : stream) {
                images.add(dirFile.toString());
            }
            return images;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            throw new BusinessException("Erro ao carregar imagens", e);
        }
    }
}
