package com.kingprice.insurance.springworkassessment.exception;

import com.kingprice.insurance.springworkassessment.exception.base.CustomError;
import com.kingprice.insurance.springworkassessment.exception.base.CustomException;

import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

public class RepositoryException extends CustomException {
    public RepositoryException(RepositoryError type) {
        super(type);
    }

    public RepositoryException(RepositoryError type, Exception e) {
        super(type, e);
    }

    public RepositoryException(RepositoryError type, String message) {
        super(type, message);
    }

    public RepositoryException(RepositoryError type, String message, Exception e) {
        super(type, message, e);
    }

    public static class RepositoryError extends CustomError {

        private final static String errorBaseName = RepositoryError.class.getSimpleName();

        public static final RepositoryError REPOSITORY_GENERAL_SQL__ERROR = new RepositoryError(
                errorBaseName+"_00099",
                "General SQL related error",
                SC_INTERNAL_SERVER_ERROR
        );

        RepositoryError(String errorCode, String reason, int httpStatusCode) {
            super(errorCode,reason,httpStatusCode);
        }
    }
}
