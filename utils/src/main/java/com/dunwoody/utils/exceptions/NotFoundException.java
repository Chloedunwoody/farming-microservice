package com.dunwoody.utils.exceptions;

public class NotFoundException extends RuntimeException{
    private NotFoundException(){}
    public NotFoundException(String message){super(message);}
    private NotFoundException(Throwable cause){super(cause);}
    private NotFoundException(Throwable cause , String message) {super(message,cause);}
}
