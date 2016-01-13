/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nerv.eva.faces.util;

import lombok.Getter;

/**
 * Classe que mapeia os dados do arquivo escolhido pelo usuário.
 * @author Vitor
 */
public class UploadFile {
    @Getter private final String fileName;
    @Getter private final byte[] contents;

    public UploadFile(String fileName, byte[] contents) {
        this.fileName = fileName;
        this.contents = contents;
    }
}
