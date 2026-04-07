package com.horadorango.projetohoradorango.domain.exeption;

public class DataConflictExeption extends RuntimeException {

    public DataConflictExeption(String message) {
        super(message);
    }
}
