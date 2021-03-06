package com.megapapa.impulse.broadcaster.exception;

public class TranslationException extends RuntimeException {
    public TranslationException() {
        super();
    }

    public TranslationException(String message) {
        super(message);
    }

    public TranslationException(String message, Throwable cause) {
        super(message, cause);
    }

    public TranslationException(Throwable cause) {
        super(cause);
    }

    protected TranslationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
