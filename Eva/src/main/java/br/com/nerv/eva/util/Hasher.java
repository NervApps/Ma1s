/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nerv.eva.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.enterprise.context.RequestScoped;

/**
 * Classe que encapsula funcionalidades de criptografia de dados.
 * @author Vitor
 */
@RequestScoped
public class Hasher {
    
    /**
     * Aplica um algorítimo de hash no texto.
     * @param text O texto que será convertido em hash.
     * @return O texto convertido em hash.
     */
    public String hash(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(text.getBytes());

            return parse(md);
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
    }
    
    private String parse(MessageDigest md) {
        byte[] hash = md.digest();
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hash.length; i++) {
            sb.append(Integer.toHexString(hash[i]));
        }
        
        return sb.toString();
    }
}