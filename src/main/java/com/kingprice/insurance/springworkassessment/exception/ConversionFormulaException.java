package com.kingprice.insurance.springworkassessment.exception;

import com.kingprice.insurance.springworkassessment.exception.base.CustomError;
import com.kingprice.insurance.springworkassessment.exception.base.CustomException;

import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

public class ConversionFormulaException extends CustomException {
    public ConversionFormulaException(ConversionFormulaError type) {
        super(type);
    }

    public ConversionFormulaException(ConversionFormulaError type, Exception e) {
        super(type, e);
    }

    public ConversionFormulaException(ConversionFormulaError type, String message) {
        super(type, message);
    }

    public ConversionFormulaException(ConversionFormulaError type, String message, Exception e) {
        super(type, message, e);
    }

    public static class ConversionFormulaError extends CustomError {

        private final static String errorBaseName = ConversionFormulaError.class.getSimpleName();

        public static final ConversionFormulaError INTERNAL_SERVER_ERROR = new ConversionFormulaError(
                "00500_" + errorBaseName,
                "Internal Server Error",
                SC_INTERNAL_SERVER_ERROR
        );
        public static final ConversionFormulaError CALCULATION_GENERAL_ERROR = new ConversionFormulaError(
                "00401_" + errorBaseName,
                "Failed to create/apply conversion formula",
                SC_BAD_REQUEST
        );

        public ConversionFormulaError(String errorCode, String reason, int httpStatusCode) {
            super(errorCode, reason, httpStatusCode);
        }
    }
}
