/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.web.context.validator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.validation.ValidationException;

/**
 *
 * @author Vitor
 */
@Named("imgType") @RequestScoped
public class ImageTypeValidator {
    
    private static final int MB = 1024;
    private static final int MAX = 5120;
    private static final List<String> allowedTypes =
            Arrays.asList(".png", ".jpeg", ".jpg");

    public void validate(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Part) {
            final Part file = (Part) value;
            validateType(file.getSubmittedFileName());
            validateSize(file.getSize());
        }
    }
    
    private void validateType(final String name) {
        if (!allowedTypes.contains(name.substring(name.indexOf(".")))) {
            throw new ValidationException("Tipo de arquivo n�o suportado");
        }
    }
    
    private void validateSize(final Long size) {
        if (size > MB) {
            final BigInteger result = new BigDecimal(size)
                                        .divide(new BigDecimal(MB))
                                        .toBigInteger();
            
            if (result.intValue() > MAX)
                throw new ValidationException("A imagem cont�m mais que 5MB");
        }
    }
}