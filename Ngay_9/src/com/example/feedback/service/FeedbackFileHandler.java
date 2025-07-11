package com.example.feedback.service;

import com.example.feedback.model.CustomerFeedback;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackFileHandler {

    private final Path feedbackPath = Paths.get("feedback.csv");
    private final Path reportPath = Paths.get("report.txt");

    // Tạo file CSV mẫu
    public void createSampleFile() throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("customerId,name,rating,comment");
        for (int i = 1; i <= 10; i++) { // Demo 10 dòng cho nhanh, đổi 10000 nếu cần.
            lines.add(1000 + i + ",Customer" + i + "," + (i % 5 + 1) + ",\"Sample comment " + i + "\"");
        }
        Files.write(feedbackPath, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        System.out.println("Sample file created: " + feedbackPath.toAbsolutePath());
    }

    // Đọc file
    public List<CustomerFeedback> readFeedbackFile() throws IOException {
        List<CustomerFeedback> feedbacks = new ArrayList<>();

        if (!Files.exists(feedbackPath)) {
            System.out.println("File does not exist: " + feedbackPath);
            return feedbacks;
        }

        List<String> lines = Files.readAllLines(feedbackPath);
        for (int i = 1; i < lines.size(); i++) { // Bỏ header
            String line = lines.get(i);
            try {
                String[] parts = line.split(",", 4);
                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                int rating = Integer.parseInt(parts[2].trim());
                String comment = parts[3].trim().replace("\"", "");
                feedbacks.add(new CustomerFeedback(id, name, rating, comment));
            } catch (Exception e) {
                System.err.println("Invalid line: " + line);
            }
        }

        return feedbacks;
    }

    // Ghi file kết quả
    public void writeReport(List<String> reportLines) throws IOException {
        Files.write(reportPath, reportLines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        System.out.println("Report written: " + reportPath.toAbsolutePath());
    }

    // Xoá file feedback.csv
    public void deleteFeedbackFile() throws IOException {
        Files.deleteIfExists(feedbackPath);
        System.out.println("Deleted feedback.csv for security.");
    }
}