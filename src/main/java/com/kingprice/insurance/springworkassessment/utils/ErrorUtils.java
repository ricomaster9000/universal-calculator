package com.kingprice.insurance.springworkassessment.utils;

import jakarta.validation.ConstraintViolationException;

import java.util.Arrays;
import java.util.List;

public class ErrorUtils {

    public static String getSensibleErrorMessage(Exception e) {
        String result;
        if(e instanceof ConstraintViolationException constrainValidation) {
            result = Arrays.toString(ErrorUtils.getSensibleConstraintValidationMessages(constrainValidation));
        } else {
            result = e.getMessage();
        }
        return result;
    }

    public static String[] getSensibleConstraintValidationMessages(ConstraintViolationException e) {
        List<String> msgList = e.getConstraintViolations().stream().map(constraintValidation -> constraintValidation.getMessage() + " for " + constraintValidation.getPropertyPath()).toList();
        return msgList.toArray(new String[0]);
    }

}
