package com.cielo.clientservice.clientsservice.enums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.IllegalFormatException;

public enum IssueEnum {
    OBJETO_NAO_ACHADO(1,"%s não encontrada(o)"),
    UPDATE_NAO_ACHADO(2,"Falha na atualização do objeto %s, objeto não existe"),
    CONFLITO(3,"Falha no cadastro do objeto %s, %s já esta em uso"),
    OBJETO_ERRADO(4,"Payload com atributo(s) errado(s):"),
    FILA_VAZIA(5, "Fila de atendimento vazia");

    private static final Logger LOGGER = LoggerFactory.getLogger(IssueEnum.class);
    private final int code;
    private final String message;

    IssueEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getFormattedMessage(final Object... args){
        if (message == null){
          return "";
        };
        try{
            return String.format(message, args);
        }catch (final IllegalFormatException ex){
            LOGGER.warn(ex.getMessage());
            return message.replace("%s", "");
        }
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
