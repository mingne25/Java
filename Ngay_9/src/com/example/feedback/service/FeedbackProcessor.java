package com.example.feedback.service;

import com.example.feedback.model.CustomerFeedback;

import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FeedbackProcessor {

    // Predicate: hài lòng nếu rating >= 4
    public static final Predicate<CustomerFeedback> isSatisfied = fb -> fb.getRating() >= 4;

    // Consumer: in phản hồi
    public static final Consumer<CustomerFeedback> displayFeedback =
            System.out::println; // method reference

    // Function: convert to String for report
    public static final Function<CustomerFeedback, String> feedbackToString = fb ->
            fb.getCustomerId() + " - " + fb.getName() + ": " + fb.getRating() + " stars";

    // Supplier: tạo phản hồi mẫu nếu file trống
    public static final Supplier<CustomerFeedback> defaultFeedbackSupplier =
            () -> new CustomerFeedback(9999, "DefaultUser", 5, "No feedback available");

    public List<CustomerFeedback> filterUnsatisfied(List<CustomerFeedback> feedbacks) {
        return feedbacks.stream()
                .filter(fb -> fb.getRating() < 4)
                .collect(Collectors.toList());
    }

    public List<String> analyze(List<CustomerFeedback> feedbacks) {
        List<String> report = feedbacks.stream()
                .map(feedbackToString)
                .collect(Collectors.toList());

        double avgRating = feedbacks.stream()
                .mapToInt(CustomerFeedback::getRating)
                .average()
                .orElse(0.0);

        long countNegative = feedbacks.stream()
                .filter(fb -> fb.getRating() < 3)
                .count();

        // Group by rating
        var grouped = feedbacks.stream()
                .collect(Collectors.groupingBy(CustomerFeedback::getRating, Collectors.counting()));

        // Partition by positive / negative
        var partitioned = feedbacks.stream()
                .collect(Collectors.partitioningBy(fb -> fb.getRating() >= 4));

        report.add("\n--- ANALYSIS ---");
        report.add("Average Rating: " + avgRating);
        report.add("Negative Feedbacks (<3): " + countNegative);
        report.add("Grouped by Rating: " + grouped);
        report.add("Partitioned Positive/Negative: " + partitioned);

        return report;
    }

    public void handleOptionalComments(List<CustomerFeedback> feedbacks) {
        feedbacks.forEach(fb -> {
            String comment = fb.getComment().orElse("No comment provided");
            System.out.println(fb.getName() + ": " + comment);
        });
    }
}