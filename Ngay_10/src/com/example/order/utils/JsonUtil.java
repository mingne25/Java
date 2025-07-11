package com.example.order.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.example.order.model.Order;

import java.io.File;
import java.io.IOException;

public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper()
            .findAndRegisterModules()
            .enable(SerializationFeature.INDENT_OUTPUT);

    public static void writeOrderToJsonFile(Order order, String filePath) throws IOException {
        mapper.writeValue(new File(filePath), order);
    }

    public static Order readOrderFromJsonFile(String filePath) throws IOException {
        return mapper.readValue(new File(filePath), Order.class);
    }

    public static String serialize(Order order) throws IOException {
        return mapper.writeValueAsString(order);
    }

    public static Order deserialize(String json) throws IOException {
        return mapper.readValue(json, Order.class);
    }
}
