package com.horadorango.projetohoradorango.domain.exeption;

public class DataConflictException extends RuntimeException {

    public DataConflictException(String message) {
        super(message);
    }
}
