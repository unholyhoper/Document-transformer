package com.infor.exception;

public class UnsupportedFileExtensionException extends RuntimeException {

    public UnsupportedFileExtensionException() {
    }

    public UnsupportedFileExtensionException(String message) {
        super(message);
    }

    public UnsupportedFileExtensionException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedFileExtensionException(Throwable cause) {
        super(cause);
    }

    public UnsupportedFileExtensionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
