/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.common.exception.handler;

import com.app.common.dto.error.AppCommonErrorResponse;
import com.app.common.exception.AppCommonException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author schigullapally
 */
@ControllerAdvice
public class ApplicationGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AppCommonException.class)
    public ResponseEntity<AppCommonErrorResponse> customException(AppCommonException ex, WebRequest request) {

        AppCommonErrorResponse errorResponse = new AppCommonErrorResponse();

        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setErrorCode(ex.getErrCode());
        errorResponse.setErrorMsg(ex.getErrMsg());

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (HttpStatus.valueOf(new Integer(ex.getErrCode())) != null) {
            status = HttpStatus.valueOf(new Integer(ex.getErrCode()));
        }

        return new ResponseEntity<>(errorResponse, status);

    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<AppCommonErrorResponse> customException(UsernameNotFoundException ex, WebRequest request) {

        AppCommonErrorResponse errorResponse = new AppCommonErrorResponse();

        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setErrorCode(HttpStatus.NOT_FOUND.value());
        errorResponse.setErrorMsg(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public void constraintViolationException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    // error handle for @Valid
    @Override
    protected ResponseEntity<Object>
            handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                    HttpHeaders headers,
                    HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        //Get all fields errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);

    }
}
