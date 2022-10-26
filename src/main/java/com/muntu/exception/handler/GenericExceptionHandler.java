package com.muntu.exception.handler;

import com.muntu.config.ApplicationPropertyReader;
import com.muntu.constants.Constants;
import com.muntu.exception.AppointmentNotFoundException;
import com.muntu.exception.CustomValidationFailureException;
import com.muntu.exception.TimeNotAvailableException;
import com.muntu.rest.RestAPICode;
import com.muntu.rest.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.muntu.exception.Error;
import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GenericExceptionHandler {

    @Autowired
    private ApplicationPropertyReader propertyReader;

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = CustomValidationFailureException.class)
    public RestResponse<?> handleValidationFailureException(CustomValidationFailureException exception) {
        return RestResponse.of(propertyReader.getProperty(Constants.VALIDATION_FAILURE),
                RestAPICode.BAD_REQUEST_STATUS_CODE, LocalDateTime.now(),
                Error.of(exception.getField(), exception.getMessage()));
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = AppointmentNotFoundException.class)
    public RestResponse<?> appointmentNotFoundExceptionHandler(AppointmentNotFoundException exception) {
        return RestResponse.of(propertyReader.getProperty(Constants.VALIDATION_FAILURE),
                RestAPICode.BAD_REQUEST_STATUS_CODE, LocalDateTime.now(),
                Error.of(exception.getField(), exception.getMessage()));
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = TimeNotAvailableException.class)
    public RestResponse<?> TimeNotAvailableExceptionHandler(TimeNotAvailableException exception) {
        return RestResponse.of(propertyReader.getProperty(Constants.VALIDATION_FAILURE),
                RestAPICode.BAD_REQUEST_STATUS_CODE, LocalDateTime.now(),
                Error.of(exception.getField(), exception.getMessage()));
    }


    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public Error handleRootException(Exception rootException) {
        log.info("Inside rootException Msg {}", rootException.getMessage());
        rootException.printStackTrace();
        return new Error(((Integer)HttpStatus.INTERNAL_SERVER_ERROR.value()).toString(), propertyReader.getProperty(Constants.INTERNAL_SERVER_ERROR) );
    }
}