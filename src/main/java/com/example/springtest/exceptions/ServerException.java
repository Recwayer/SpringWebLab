package com.example.springtest.exceptions;

public abstract class ServerException extends RuntimeException {
    public ServerException(String message) {
        super(message);
    }

    public ServerException(Throwable cause) {
        super(cause);
    }

    public ServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public static class NotFoundException extends ServerException {

        public NotFoundException(String message) {
            super(message);
        }

        public NotFoundException(Throwable cause) {
            super(cause);
        }

        public NotFoundException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class SaveFileException extends ServerException {
        public SaveFileException(String message) {
            super(message);
        }

        public SaveFileException(Throwable cause) {
            super(cause);
        }

        public SaveFileException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
