package com.example.springtest.exceptions;

public abstract class ClientException extends RuntimeException{
    public ClientException(String message) {
        super(message);
    }

    public ClientException(Throwable cause) {
        super(cause);
    }

    public static class NotFoundException extends ClientException{
        public NotFoundException(Throwable cause) {
            super(cause);
        }

        public NotFoundException(String message) {
            super(message);

        }
    }
    public static class BadRequestException extends ClientException{
        public BadRequestException(Throwable cause) {
            super(cause);
        }

        public BadRequestException(String message) {
            super(message);

        }
    }
    public static class InvalidInputException extends ClientException{
        public InvalidInputException(Throwable cause) {
            super(cause);
        }
        public InvalidInputException(String message) {
            super(message);
        }
    }
}
