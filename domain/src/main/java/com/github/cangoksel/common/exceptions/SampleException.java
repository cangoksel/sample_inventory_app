package com.github.cangoksel.common.exceptions;

/**
 * Created by herdemir on 17.03.2015.
 */
public class SampleException extends RuntimeException {
    public SampleException() {
    }

    public SampleException(String message) {
        super(message);
    }

    public SampleException(String message, Throwable cause) {
        super(message, cause);
    }

    public SampleException(Throwable cause) {
        super(cause);
    }

    public SampleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
