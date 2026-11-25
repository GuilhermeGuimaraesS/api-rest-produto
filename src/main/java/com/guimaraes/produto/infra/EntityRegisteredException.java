package com.guimaraes.produto.infra;

public class EntityRegisteredException extends RuntimeException{

    public EntityRegisteredException(String message) {
        super(message);
    }
}
