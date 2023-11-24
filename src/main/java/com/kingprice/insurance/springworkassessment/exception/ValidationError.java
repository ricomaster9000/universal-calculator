package com.kingprice.insurance.springworkassessment.exception;

import com.kingprice.insurance.springworkassessment.exception.base.CustomError;

import java.util.Map;

public class ValidationError extends CustomError {

    private Map<String, String> errors;

    public ValidationError(String errorCode, int httpStatusCode, String message) {
        super(errorCode, message, httpStatusCode);
    }

    public ValidationError(String errorCode, int httpStatusCode, String message, Map<String, String> errors) {
        super(errorCode, message, httpStatusCode);
        this.errors = errors;
    }

    // Getters and Setters
    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
