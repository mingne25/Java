package model;

public class Booking {
    private String userEmail;
    private String eventId;
    private String seatNumber;

    public Booking(String userEmail, String eventId, String seatNumber) {
        this.userEmail = userEmail;
        this.eventId = eventId;
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return String.format("Email: %s | Event ID: %s | Số ghế: %s", userEmail, eventId, seatNumber);
    }

    // Getter methods
    public String getUserEmail() { return userEmail; }
    public String getEventId() { return eventId; }
    public String getSeatNumber() { return seatNumber; }
}