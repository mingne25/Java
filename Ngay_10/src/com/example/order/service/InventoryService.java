package com.example.order.service;

import com.example.order.model.Order;

public interface InventoryService {
    boolean checkInventory(Order order);
}
