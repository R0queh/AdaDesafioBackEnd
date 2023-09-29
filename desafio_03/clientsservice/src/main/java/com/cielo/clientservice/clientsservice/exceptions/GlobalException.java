package com.cielo.clientservice.clientsservice.exceptions;

import org.springframework.http.HttpStatus;

public class GlobalException extends  RuntimeException{

    private static final long serialVersionUID = 1L;
    private final Issue issue;
    private final HttpStatus httpStatus;

    public GlobalException(final Issue issue, final HttpStatus httpStatus){
        super(issue.getMessage());
        this.issue = issue;
        this.httpStatus = httpStatus;
    }

    public GlobalException(final Issue issue, final Throwable cause,final HttpStatus httpStatus){
        super(issue.getMessage(), cause);
        this.issue = issue;
        this.httpStatus = httpStatus;
    }


    public Issue getIssue() {
        return issue;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
