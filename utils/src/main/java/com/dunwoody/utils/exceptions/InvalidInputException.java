package com.dunwoody.utils.exceptions;

public class InvalidInputException extends RuntimeException {

    private InvalidInputException(){}
    public InvalidInputException(String message){super(message);}
    private InvalidInputException(Throwable cause){super(cause);}
    private InvalidInputException(Throwable cause , String message) {super(message,cause);}
}
