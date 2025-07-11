package com.example.finance;

import com.example.finance.model.ExchangeRate;
import com.example.finance.processor.DataProcessor;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<ExchangeRate> rates = new ArrayList<>();
        rates.add(new ExchangeRate(1620000000L, 23.2));
        rates.add(new ExchangeRate(1620000500L, 23.4));
        rates.add(new ExchangeRate(1620001000L, 23.5));

        DataProcessor<ExchangeRate> processor = new DataProcessor<>();
        List<ExchangeRate> filtered = processor.filterByTimeRange(rates, 1620000000L, 1620000500L);

        System.out.println("Filtered size: " + filtered.size());
        System.out.println("Average: " + processor.average(filtered));
        System.out.println("Max: " + processor.max(filtered));
        System.out.println("Min: " + processor.min(filtered));
    }
}