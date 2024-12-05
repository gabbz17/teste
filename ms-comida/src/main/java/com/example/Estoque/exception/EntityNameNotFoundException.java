package com.example.Estoque.exception;

public class EntityNameNotFoundException extends RuntimeException{
    public EntityNameNotFoundException(String error){
        super(error);
    }
}
