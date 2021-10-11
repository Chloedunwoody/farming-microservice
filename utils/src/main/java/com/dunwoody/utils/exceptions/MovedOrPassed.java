package com.dunwoody.utils.exceptions;
//410
public class MovedOrPassed extends RuntimeException {
    private MovedOrPassed(){}
    public MovedOrPassed(String message){super(message);}
    private MovedOrPassed(Throwable cause){super(cause);}
    private MovedOrPassed(Throwable cause , String message) {super(message,cause);}

}
