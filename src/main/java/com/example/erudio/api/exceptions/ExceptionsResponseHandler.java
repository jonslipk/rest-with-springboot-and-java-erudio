package com.example.erudio.api.exceptions;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.validation.method.MethodValidationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.erudio.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ExceptionsResponseHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ExecptionResponse> handleNotFoundExceptions(Exception ex, WebRequest wr) {
        ExecptionResponse execptionResponse = new ExecptionResponse(new Date(), ex.getMessage(),
                wr.getDescription(false));
        return new ResponseEntity<>(execptionResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    @Nullable
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NonNull MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest wr) {
        ExecptionResponse execptionResponse = new ExecptionResponse(new Date(), ex.getFieldError().getDefaultMessage(),
                wr.getDescription(false));
        return new ResponseEntity<>(execptionResponse, HttpStatus.NOT_FOUND);
    }

}
