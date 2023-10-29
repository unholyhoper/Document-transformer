package com.infor.exception;

public class FileWithoutExtensionException extends RuntimeException{
    public FileWithoutExtensionException() {
    }

    public FileWithoutExtensionException(String message) {
        super(message);
    }

    public FileWithoutExtensionException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileWithoutExtensionException(Throwable cause) {
        super(cause);
    }

    public FileWithoutExtensionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
