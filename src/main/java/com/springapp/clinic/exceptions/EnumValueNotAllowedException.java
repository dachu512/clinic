package com.springapp.clinic.exceptions;

public class EnumValueNotAllowedException extends RuntimeException {

    public EnumValueNotAllowedException(String message) {
        super(message);
    }
}
