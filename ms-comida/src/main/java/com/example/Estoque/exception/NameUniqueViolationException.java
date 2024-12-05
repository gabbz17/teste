package com.example.Estoque.exception;

public class NameUniqueViolationException extends RuntimeException{

    public NameUniqueViolationException(String error){
        super(error);
    }
}
