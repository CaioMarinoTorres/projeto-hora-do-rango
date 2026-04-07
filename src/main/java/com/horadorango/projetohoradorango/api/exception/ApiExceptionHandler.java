package com.horadorango.projetohoradorango.api.exception;

import com.horadorango.projetohoradorango.domain.exeption.DataConflictExeption;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataConflictExeption.class)
    public ProblemDetail handleNegocio (DataConflictExeption e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setTitle(e.getMessage());
        return problemDetail;
    }

}
