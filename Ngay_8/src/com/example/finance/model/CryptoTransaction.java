package com.example.finance.model;

public class CryptoTransaction implements DataPoint {
    private long timestamp;
    private double value; // giá trị giao dịch

    public CryptoTransaction(long timestamp, double value) {
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