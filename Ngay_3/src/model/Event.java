package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event {
    private String eventId;
    private String eventName;
    private String location;
    private LocalDate date;
    private int maxSeats;

    public Event(String eventId, String eventName, String location, LocalDate date, int maxSeats) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.location = location;
        this.date = date;
        this.maxSeats = maxSeats;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Tên: %s | Địa điểm: %s | Ngày: %s | Số chỗ tối đa: %d",
                eventId, eventName, location, date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), maxSeats);
    }

    // Getter methods
    public String getEventId() { return eventId; }
    public String getEventName() { return eventName; }
    public String getLocation() { return location; }
    public LocalDate getDate() { return date; }
    public int getMaxSeats() { return maxSeats; }
}