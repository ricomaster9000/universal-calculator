package org.greatgamesonly.core.universalcalculator.configuration;

import org.greatgamesonly.core.universalcalculator.exception.ValidationError;
import org.greatgamesonly.core.universalcalculator.exception.base.CustomError;
import org.greatgamesonly.core.universalcalculator.exception.base.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String VALIDATION_ERROR_CODE = "VALIDATION_400";

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException ex) {
        logger.error("An error occurred: {}", ex.getMessage(), ex);
        CustomError customError = ex.getCustomError();
        HttpStatus status = HttpStatus.resolve(customError.getHttpStatusCode()) != null
                ? HttpStatus.resolve(customError.getHttpStatusCode())
                : HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(customError, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ValidationError validationError = new ValidationError(
                VALIDATION_ERROR_CODE,
                HttpStatus.BAD_REQUEST.value(),
                "Validation failed",
                errors
        );

        logger.error("Validation error: {}", validationError.getErrors());

        return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<CustomError> handlePayloadNotSetException(HttpMessageNotReadableException ex) {
        String responseMsg = ex.getMessage().substring(0,ex.getMessage().indexOf(":"));

        ValidationError validationError = new ValidationError(
                VALIDATION_ERROR_CODE,
                HttpStatus.BAD_REQUEST.value(),
                responseMsg
        );
        logger.error("An error occurred: {}", validationError.getErrors());
        return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        logger.error("An unexpected error occurred: {}", ex.getMessage(), ex);
        return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
