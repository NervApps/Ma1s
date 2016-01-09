package br.com.nerv.eva.faces.exception;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Fábrica de listener de exceções.
 * @author Vitor
 */
public class CustomExceptionHandlerFactory extends ExceptionHandlerFactory {
    
    private final ExceptionHandlerFactory factory;

    public CustomExceptionHandlerFactory(ExceptionHandlerFactory factory) {
        this.factory = factory;
    }

    @Override
    public ExceptionHandler getExceptionHandler() {
        return new CustomExceptionHandler(factory.getExceptionHandler());
    }
}