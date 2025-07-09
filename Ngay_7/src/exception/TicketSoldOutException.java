package exception;

public class TicketSoldOutException extends Exception {
    public TicketSoldOutException(String message) {
        super(message);
    }

    public TicketSoldOutException(String message, Throwable cause) {
        super(message, cause);
    }
}