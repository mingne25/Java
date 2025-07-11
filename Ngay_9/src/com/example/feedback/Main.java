package com.example.feedback;

import com.example.feedback.model.CustomerFeedback;
import com.example.feedback.service.FeedbackFileHandler;
import com.example.feedback.service.FeedbackProcessor;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FeedbackFileHandler fileHandler = new FeedbackFileHandler();
        FeedbackProcessor processor = new FeedbackProcessor();

        try {
            fileHandler.createSampleFile();

            List<CustomerFeedback> feedbacks = fileHandler.readFeedbackFile();

            if (feedbacks.isEmpty()) {
                feedbacks.add(FeedbackProcessor.defaultFeedbackSupplier.get());
            }

            System.out.println("--- All Feedbacks ---");
            feedbacks.forEach(FeedbackProcessor.displayFeedback);

            System.out.println("\n--- Unsatisfied Feedbacks (rating <4) ---");
            processor.filterUnsatisfied(feedbacks)
                    .forEach(FeedbackProcessor.displayFeedback);

            processor.handleOptionalComments(feedbacks);

            List<String> reportLines = processor.analyze(feedbacks);
            fileHandler.writeReport(reportLines);

            fileHandler.deleteFeedbackFile();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}