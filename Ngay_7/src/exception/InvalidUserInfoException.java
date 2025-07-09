package exception;

public class InvalidUserInfoException extends Exception {
    public InvalidUserInfoException(String message) {
        super(message);
    }

    public InvalidUserInfoException(String message, Throwable cause) {
        super(message, cause);
    }
}