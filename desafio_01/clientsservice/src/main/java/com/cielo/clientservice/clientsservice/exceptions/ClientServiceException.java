package com.cielo.clientservice.clientsservice.exceptions;

import com.cielo.clientservice.clientsservice.enums.IssueEnum;
import org.springframework.http.HttpStatus;

public class ClientServiceException extends GlobalException{
    public ClientServiceException(Issue issue, HttpStatus httpStatus) {
        super(issue, httpStatus);
    }

    public static ClientServiceException conflito(final String entity, final String document){
        return new ClientServiceException(new Issue(IssueEnum.CONFLITO, entity, document), HttpStatus.CONFLICT);
    }

    public static ClientServiceException naoAchado(final String entity){
        return new ClientServiceException(new Issue(IssueEnum.OBJETO_NAO_ACHADO, entity), HttpStatus.NOT_FOUND);
    }

    public static ClientServiceException naoAchadoAtualizacao(final String entity){
        return new ClientServiceException(new Issue(IssueEnum.UPDATE_NAO_ACHADO, entity), HttpStatus.NOT_FOUND);
    }
}
