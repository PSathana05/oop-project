

import java.util.*;

public class ReportManager {
    public void generateReport(String bookingFile, String cancelFile, String reportFile) {
        List<String> bookings = FileUtils.readFile(bookingFile);
        List<String> cancellations = FileUtils.readFile(cancelFile);
        List<String> report = new ArrayList<>();

        report.add("=== Daily Report ===");
        report.add("");
        report.add("Bookings:");
        if (bookings.isEmpty()) report.add("No bookings today.");
        else report.addAll(bookings);

        report.add("");
        report.add("Cancellations:");
        if (cancellations.isEmpty()) report.add("No cancellations today.");
        else report.addAll(cancellations);

        FileUtils.writeFile(reportFile, report, false);
        System.out.println("Report generated successfully: " + reportFile);
    }
}
