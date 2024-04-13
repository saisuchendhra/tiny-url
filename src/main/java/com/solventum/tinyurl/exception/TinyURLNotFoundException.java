package com.solventum.tinyurl.exception;

public class TinyURLNotFoundException extends RuntimeException{
    public TinyURLNotFoundException(String message){
        super(message);
    }
}
