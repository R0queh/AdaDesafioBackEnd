package com.cielo.clientservice.clientsservice.exceptions;

import com.cielo.clientservice.clientsservice.enums.IssueEnum;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

    @ExceptionHandler({GlobalException.class})
    public ResponseEntity<Issue> handleGlobalException(final GlobalException globalException){
        LOGGER.error("Exception {} - Message {}", globalException.getClass(), globalException.getIssue().getMessage());
        return new ResponseEntity<>(globalException.getIssue(), globalException.getHttpStatus());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Issue> handleConstraintViolationException(final ConstraintViolationException exception){
        List<String> constraintViolationList = new ArrayList<>();
        Iterator<ConstraintViolation<?>> iterator = exception.getConstraintViolations().iterator();
        do{
            constraintViolationList.add(iterator.next().getMessage());
        }while (iterator.hasNext());
        return new ResponseEntity<>(new Issue(IssueEnum.OBJETO_ERRADO, constraintViolationList), HttpStatus.BAD_REQUEST);
    }
}
