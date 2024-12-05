package com.example.Estoque.exception;

public class EntityIdNotFoundException extends RuntimeException{

    public EntityIdNotFoundException (String error){
        super(error);
    }
}
