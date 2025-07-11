package com.example.finance.processor;

import com.example.finance.model.DataPoint;

import java.util.ArrayList;
import java.util.List;

public class DataProcessor<T extends DataPoint> {

    public List<T> filterByTimeRange(List<T> data, long from, long to) {
        List<T> result = new ArrayList<>();
        for (T item : data) {
            if (item.getTimestamp() >= from && item.getTimestamp() <= to) {
                result.add(item);
            }
        }
        return result;
    }

    public double average(List<? extends DataPoint> data) {
        double sum = 0;
        for (DataPoint item : data) {
            sum += item.getValue();
        }
        return data.size() > 0 ? sum / data.size() : 0;
    }

    public double max(List<? extends DataPoint> data) {
        double max = Double.MIN_VALUE;
        for (DataPoint item : data) {
            if (item.getValue() > max) {
                max = item.getValue();
            }
        }
        return max;
    }

    public double min(List<? extends DataPoint> data) {
        double min = Double.MAX_VALUE;
        for (DataPoint item : data) {
            if (item.getValue() < min) {
                min = item.getValue();
            }
        }
        return min;
    }

    // Ví dụ dùng List<? super ExchangeRate> để thêm dữ liệu
    public void addExchangeRate(List<? super com.example.finance.model.ExchangeRate> list, com.example.finance.model.ExchangeRate rate) {
        list.add(rate);
    }
}