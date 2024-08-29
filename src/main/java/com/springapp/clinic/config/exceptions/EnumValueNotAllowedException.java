package com.springapp.clinic.config.exceptions;

public class EnumValueNotAllowedException extends RuntimeException {

    public EnumValueNotAllowedException(String message) {
        super(message);
    }
}
