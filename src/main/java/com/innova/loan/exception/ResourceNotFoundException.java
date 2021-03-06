package com.innova.loan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

/**
 * For throw unique exceptions.
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException implements Serializable {

    private static final Long serialVersionUID=-1475715245L;

    public ResourceNotFoundException(String message){
        super(message);
    }
}
