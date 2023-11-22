package com.kingprice.insurance.springworkassessment.exception;

import com.kingprice.insurance.springworkassessment.exception.base.CustomError;
import com.kingprice.insurance.springworkassessment.exception.base.CustomException;

import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

public class CalculationException extends CustomException {
    public CalculationException(CalculationError type) {
        super(type);
    }

    public CalculationException(CalculationError type, Exception e) {
        super(type, e);
    }

    public CalculationException(CalculationError type, String message) {
        super(type, message);
    }

    public CalculationException(CalculationError type, String message, Exception e) {
        super(type, message, e);
    }

    public static class CalculationError extends CustomError {

        private final static String errorBaseName = CalculationError.class.getSimpleName();

        public static final CalculationError INTERNAL_SERVER_ERROR = new CalculationError(
                "00500_" + errorBaseName,
                "Internal Server Error",
                SC_INTERNAL_SERVER_ERROR
        );
        public static final CalculationError CALCULATION_GENERAL_ERROR = new CalculationError(
                "00401_" + errorBaseName,
                "Failed to create/do calculation",
                SC_BAD_REQUEST
        );

        public static final CalculationError CALCULATION_INCORRECT_FORMULA_LINKED_TO_CALCULATION = new CalculationError(
                "00402_" + errorBaseName,
                "Failed to create/do calculation",
                SC_BAD_REQUEST
        );

        public static final CalculationError CALCULATION_UNABLE_TO_FIND_CONVERSION_FACTOR = new CalculationError(
                "00403_" + errorBaseName,
                "Failed to find conversion factor used in conversion formula calculations",
                SC_BAD_REQUEST
        );

        public CalculationError(String errorCode, String reason, int httpStatusCode) {
            super(errorCode, reason, httpStatusCode);
        }
    }
}
