package com.gopi.rest.webservices.restfulwebservices.user.exception;

import java.util.Date;

public class ExceptionResponse {

    private Date timestamp;
    private String message;
    private String trace;

    public ExceptionResponse(Date timestamp, String message, String trace) {
        this.timestamp = timestamp;
        this.message = message;
        this.trace = trace;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }
}
