package com.example.finance.model;

public class StockPrice implements DataPoint {
    private long timestamp;
    private double value; // giá cổ phiếu

    public StockPrice(long timestamp, double value) {
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