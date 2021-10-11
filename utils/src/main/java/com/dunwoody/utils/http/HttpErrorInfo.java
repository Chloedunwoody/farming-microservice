package com.dunwoody.utils.http;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
//added to avoid NO BEAN SERIALIZABLE
@JsonSerialize
public class HttpErrorInfo {
    private ZonedDateTime timeStamp;
    private String path;
    private HttpStatus httpStatus;
    private String message;

    public HttpErrorInfo( HttpStatus httpStatus,String path, String message) {
        timeStamp = ZonedDateTime.now();
        this.httpStatus = httpStatus;
        this.path = path;
        this.message = message;
    }

    public ZonedDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getPath() {
        return path;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
