package com.github.cangoksel.common.exceptions;

/**
 * Created by herdemir on 16.03.2015.
 */
public class IllegalEntityAccessException extends RuntimeException {
    public IllegalEntityAccessException() {
    }

    public IllegalEntityAccessException(String message) {
        super(message);
    }

    public IllegalEntityAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalEntityAccessException(Throwable cause) {
        super(cause);
    }

    public IllegalEntityAccessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
