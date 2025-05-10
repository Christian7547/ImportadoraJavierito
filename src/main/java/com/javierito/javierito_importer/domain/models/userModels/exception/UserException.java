package com.javierito.javierito_importer.domain.models.userModels.exception;

public class UserException {
    public class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(long userId) {
            super("Usuario con ID " + userId + "");
        }
    }

    public static class InvalidUserDataException extends RuntimeException {
        public InvalidUserDataException(String message) {
            super(message);
        }
    }

    public class UserEmailNotFoundException extends RuntimeException {
        public UserEmailNotFoundException(String email) {
            super("No se encontró usuario con email: " + email);
        }
    }

    public class EmailSendingException extends RuntimeException {
        public EmailSendingException(String message) {
            super(message);
        }

        public EmailSendingException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
