package com.example.finance.model;

public class ExchangeRate implements DataPoint {
    private long timestamp;
    private double value; // tỷ giá

    public ExchangeRate(long timestamp, double value) {
        this.timestamp = timestamp;
        this.value = value;
    }

    @Override
    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public double getValue() {
        return value;
    }
}