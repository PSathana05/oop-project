import java.util.*;
// Logger configuration updated
import java.util.logging.Level;
import java.util.logging.Logger;

public class Project {
    // Add logger configuration comments
    private static final Logger LOGGER = Logger.getLogger(Project.class.getName());
    private static final String FLIGHTS_FILE = "flights.txt";
    private static final String BOOKINGS_FILE = "bookings.txt";
    private static final String CANCELLATIONS_FILE = "cancellations.txt";
    private static final String REPORT_FILE = "report.txt";

    private static final FlightManager flightManager = new FlightManager();
    private static final BookingManager bookingManager = new BookingManager();
    private static final ReportManager reportManager = new ReportManager();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ensureSampleFlightsExist();

        flightManager.loadFlights(FLIGHTS_FILE);
        Map<String, Flight> flights = flightManager.getFlights();

        // FIX:Replace string concatenation with built-in formatting
        LOGGER.log(Level.INFO, "Loaded {0} flights.", flights.size());

        System.out.println("Loaded " + flights.size() + " flights.");

        while (true) {
            showMenu();
            String choice = sc.nextLine().trim();

            // FIX: Switch cases are refactored into helper methods to reduce Cognitive Complexity
            switch (choice) {
                case "1":
                    handleBooking(flights);
                    break;

                case "2":
                    handleCancellation(flights);
                    break;

                case "3":
                    handleSearch();
                    break;

                case "4":
                    reportManager.generateReport(BOOKINGS_FILE, CANCELLATIONS_FILE, REPORT_FILE);
                    break;

                case "5":
                    handleListFlights(flights);
                    break;

                case "6":
                    LOGGER.info("Exiting system... Goodbye!");
                    sc.close();
                    return;

                default:
                    LOGGER.info("Invalid choice. Enter 1–6.");
            }
        }
    }



    private static void showMenu() {
        LOGGER.info("\n--- Airline Reservation System ---");
        LOGGER.info("1. Book Ticket");
        LOGGER.info("2. Cancel Ticket");
        LOGGER.info("3. Search Flights (by Destination)");
        LOGGER.info("4. Generate Report");
        LOGGER.info("5. List All Flights");
        LOGGER.info("6. Exit");
        LOGGER.info("Enter choice: ");
    }

    private static void ensureSampleFlightsExist() {
        if (!FileUtils.exists(FLIGHTS_FILE)) {
            System.out.println("Creating sample flights.txt...");
            boolean success = FlightFileWriter.writeFile(FLIGHTS_FILE);
            if (success) System.out.println("flights.txt created successfully.");
            else System.out.println("Failed to create flights.txt.");
        }
    }
}
