package com.octl2.api.commons.exception;

import com.octl2.api.commons.OctResponse;
import com.octl2.api.commons.suberror.ApiSubError;
import com.octl2.api.commons.suberror.ApiValidatorError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class OctExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<OctResponse<String>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        log.info("handleMethodArgumentNotValid");

        List<ApiSubError> details = new ArrayList<>();
        ex.getBindingResult().getAllErrors()
                .forEach(error -> {
                    String fieldName = ((FieldError) error).getField();
                    Object rejectValue = ((FieldError) error).getRejectedValue();
                    String message = error.getDefaultMessage();

                    details.add(new ApiValidatorError(fieldName, rejectValue, message));
                });
        return new ResponseEntity<>(OctResponse.build(ErrorMessages.INVALID_VALUE, details), HttpStatus.OK);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<OctResponse<String>> handleConstraintViolationException(ConstraintViolationException ex) {
        log.info("handleConstraintViolationException. Message = {}", ex.getMessage(), ex);
        return new ResponseEntity<>(OctResponse.build(ex.getMessage(), HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<OctResponse<String>> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        log.info("handleHttpMessageNotReadable. Message = {}", ex.getMessage(), ex);
        OctInvalidInputException exception = (OctInvalidInputException) ex.getCause().getCause();
        return new ResponseEntity<>(OctResponse.build(exception.getErrMsg(), exception.getApiSubErrors()), HttpStatus.OK);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    protected ResponseEntity<OctResponse<String>> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        log.info("handleNoHandlerFoundException. Message = {}", ex.getMessage(), ex);
        return new ResponseEntity<>(OctResponse.build(ErrorMessages.NOT_FOUND), HttpStatus.OK);
    }

    @ExceptionHandler(OctException.class)
    protected ResponseEntity<OctResponse<String>> handleOctException(OctException ex) {
        log.info("handleOctException. Msg = {}", ex.getErrMsg().getMessage(), ex);
        return buildResponseEntity(ex);
    }

    @ExceptionHandler(OctEntityNotFoundException.class)
    protected ResponseEntity<OctResponse<String>> handleOctEntityNotFound(OctEntityNotFoundException ex) {
        log.info("handleOctEntityNotFound. Msg = {}", ex.getErrMsg().getMessage(), ex);
        return new ResponseEntity<>(OctResponse.build(ex.getErrMsg(), Collections.singletonList(ex.getApiMessageError())), HttpStatus.OK);
    }

    @ExceptionHandler(OctInvalidInputException.class)
    protected ResponseEntity<OctResponse<String>> handleInvalidInputRequest(OctInvalidInputException ex) {
        log.info("handleInvalidInputRequest. Msg = {}", ex.getErrMsg().getMessage(), ex);
        return new ResponseEntity<>(OctResponse.build(ex.getErrMsg(), ex.getApiSubErrors()), HttpStatus.OK);
    }

    private ResponseEntity<OctResponse<String>> buildResponseEntity(OctException ex) {
        return new ResponseEntity<>(OctResponse.buildApplicationException(ex), HttpStatus.OK);
    }
}
