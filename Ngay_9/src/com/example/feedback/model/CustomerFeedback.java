package com.example.feedback.model;

import java.util.Optional;

public class CustomerFeedback {
    private int customerId;
    private String name;
    private int rating;
    private String comment;

    public CustomerFeedback(int customerId, String name, int rating, String comment) {
        this.customerId = customerId;
        this.name = name;
        this.rating = rating;
        this.comment = comment;
    }

    public int getCustomerId() { return customerId; }
    public String getName() { return name; }
    public int getRating() { return rating; }
    public Optional<String> getComment() {
        return Optional.ofNullable(comment);
    }

    @Override
    public String toString() {
        return customerId + "," + name + "," + rating + ",\"" + comment + "\"";
    }
}