package com.horadorango.projetohoradorango.api.exception;

import com.horadorango.projetohoradorango.domain.exeption.DataConflictException;
import com.horadorango.projetohoradorango.domain.exeption.DataNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataConflictException.class)
    public ResponseEntity<?> handleDataConflictException (DataConflictException ex, WebRequest request) {
        HttpStatus httpStatus = CONFLICT;

        ProblemDetail problemDetail = this.createProblemDetail(
                ex,
                httpStatus,
                ex.getMessage(),
                ex.getMessage(),
                null,
                request
        );

        return handleExceptionInternal(ex, problemDetail, new HttpHeaders(), httpStatus, request);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<?> handleDataNotFoundException(DataNotFoundException ex, WebRequest request) {
        HttpStatus httpStatus = NOT_FOUND;

        ProblemDetail problemDetail = this.createProblemDetail(
                ex,
                httpStatus,
                ex.getMessage(),
                ex.getMessage(),
                null,
                request
        );

        return handleExceptionInternal(ex, problemDetail, new HttpHeaders(), httpStatus, request);
    }

    @Override
    protected ProblemDetail createProblemDetail(Exception ex, HttpStatusCode status, String message, String detail, Object[] detailMessageArguments, WebRequest request) {
        ProblemDetail problemDetail = super.createProblemDetail(ex, status, message, detail, detailMessageArguments, request);

        problemDetail.setDetail(detail);
        problemDetail.setProperty("message", message);

        return problemDetail;
    }

}
