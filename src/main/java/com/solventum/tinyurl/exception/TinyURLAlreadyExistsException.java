package com.solventum.tinyurl.exception;

public class TinyURLAlreadyExistsException extends RuntimeException{
    public TinyURLAlreadyExistsException(String message){
        super(message);
    }
}
