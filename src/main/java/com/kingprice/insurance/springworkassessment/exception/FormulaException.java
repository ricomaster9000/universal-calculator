package com.kingprice.insurance.springworkassessment.exception;

import com.kingprice.insurance.springworkassessment.exception.base.CustomError;
import com.kingprice.insurance.springworkassessment.exception.base.CustomException;

import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

public class FormulaException extends CustomException {
    public FormulaException(FormulaError type) {
        super(type);
    }

    public FormulaException(FormulaError type, Exception e) {
        super(type, e);
    }

    public FormulaException(FormulaError type, String message) {
        super(type, message);
    }

    public FormulaException(FormulaError type, String message, Exception e) {
        super(type, message, e);
    }

    public static class FormulaError extends CustomError {

        private final static String errorBaseName = FormulaError.class.getSimpleName();

        public static final FormulaError INTERNAL_SERVER_ERROR = new FormulaError(
                "00500_" + errorBaseName,
                "Internal Server Error",
                SC_INTERNAL_SERVER_ERROR
        );
        public static final FormulaError INVALID_FORMULA = new FormulaError(
                "00401_" + errorBaseName,
                "Invalid formula",
                SC_BAD_REQUEST
        );

        public static final FormulaError FORMULA_NOT_FOUND = new FormulaError(
                "00402_" + errorBaseName,
                "Formula does not exist",
                SC_BAD_REQUEST
        );

        public FormulaError(String errorCode, String reason, int httpStatusCode) {
            super(errorCode, reason, httpStatusCode);
        }
    }
}
