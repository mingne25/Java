package com.example.order;

import com.example.order.model.Order;
import com.example.order.model.OrderItem;
import com.example.order.processor.OrderProcessor;
import com.example.order.service.InventoryService;
import com.example.order.service.ShippingService;
import com.example.order.utils.JsonUtil;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Order order = new Order(
                "ORD123",
                "Alice",
                List.of(new OrderItem("P001", 2, 19.99)),
                39.98,
                LocalDateTime.now()
        );

        // Giả lập triển khai service (cần triển khai InventoryServiceImpl, ShippingServiceImpl)
        InventoryService inventoryService = new InventoryServiceImpl(); // Cần tạo class này
        ShippingService shippingService = new ShippingServiceImpl();   // Cần tạo class này
        OrderProcessor processor = new OrderProcessor(inventoryService, shippingService);

        if (processor.process(order)) {
            JsonUtil.writeOrderToJsonFile(order, "order.json");
            System.out.println("Order processed and saved to order.json");
        } else {
            System.out.println("Order processing failed");
        }
    }
}
