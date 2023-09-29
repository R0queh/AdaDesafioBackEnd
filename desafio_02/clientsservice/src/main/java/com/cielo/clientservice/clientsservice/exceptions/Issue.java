package com.cielo.clientservice.clientsservice.exceptions;

import com.cielo.clientservice.clientsservice.enums.IssueEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class Issue implements Serializable {

    private static final long serialVersionID = 1L;

    @JsonProperty private final int code;
    @JsonProperty private final String message;
    @JsonProperty private List<String> details;

    public Issue(IssueEnum issueEnum) {
        code = issueEnum.getCode();
        message = issueEnum.getMessage();
    }
    public Issue(final IssueEnum issueEnum, final List<String> details){
        this(issueEnum);
        this.details = details;
    };

    public Issue(final IssueEnum issueEnum, final Object... args){
        code = issueEnum.getCode();
        message = issueEnum.getFormattedMessage(args);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", details=" + details +
                '}';
    }
}
