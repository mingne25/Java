package com.example.order.model;

public class OrderItem {
    private String productId;
    private int quantity;
    private double price;

    public OrderItem() {}

    public OrderItem(String productId, int quantity, double price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
}