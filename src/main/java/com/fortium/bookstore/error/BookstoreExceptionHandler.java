package com.fortium.bookstore.error;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BookstoreExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String TITLE_AUTHOR_INDEX = "TITLEAUTHORINDEX";
    private static final String TITLE_AUTHOR_ERROR_MESSAGE = "An author should not have 2 books with the same title";

    @ExceptionHandler(DataIntegrityViolationException.class)
    private ResponseEntity<Object> handleConflict(DataIntegrityViolationException ex, WebRequest request) {
        if (rootCauseContainsKey(ex, TITLE_AUTHOR_INDEX)) {
            return handleExceptionInternal(ex, TITLE_AUTHOR_ERROR_MESSAGE, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
        }
        return handleExceptionInternal(ex, "Error while saving data", new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private boolean rootCauseContainsKey(DataIntegrityViolationException ex, String errorKey) {
        return ex.getRootCause() != null && ex.getRootCause().getMessage() != null && ex.getRootCause().getMessage().contains(errorKey);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
