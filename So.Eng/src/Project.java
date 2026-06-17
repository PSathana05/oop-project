import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

// Logger configuration updated
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
            LOGGER.info("Creating sample flights.txt...");
            boolean success = FlightFileWriter.writeFile(FLIGHTS_FILE);
            if (success) LOGGER.info("flights.txt created successfully.");
            else LOGGER.info("Failed to create flights.txt.");
        }
    }

    // Helper Method 1: Ticket Booking Logic
    private static void handleBooking(Map<String, Flight> flights) {
        LOGGER.info("Enter Flight No: ");
        String fno = sc.nextLine().trim();
        LOGGER.info("Enter Passenger Name: ");
        Passenger p1 = new Passenger(sc.nextLine().trim());
        if (bookingManager.bookTicket(flights, p1, fno, BOOKINGS_FILE)) {
            flightManager.saveFlights(FLIGHTS_FILE);
        }
    }

    // Helper Method 2: Ticket Cancellation Logic
    private static void handleCancellation(Map<String, Flight> flights) {
        LOGGER.info("Enter Flight No: ");
        String cno = sc.nextLine().trim();
        LOGGER.info("Enter Passenger Name: ");
        Passenger p2 = new Passenger(sc.nextLine().trim());
        if (bookingManager.cancelTicket(flights, p2, cno, BOOKINGS_FILE, CANCELLATIONS_FILE)) {
            flightManager.saveFlights(FLIGHTS_FILE);
        }
    }

    // Helper Method 3: Search Flights Logic
    private static void handleSearch() {
        LOGGER.info("Enter Destination: ");
        String dest = sc.nextLine().trim();
        List<Flight> results = flightManager.searchFlightsByDestination(dest);
        if (results.isEmpty()) {
            // FIX: java:S2629 - Formatting placeholder used
            LOGGER.log(Level.INFO, "No flights found to {0}.", dest);
        } else {
            LOGGER.log(Level.INFO, "Available flights to {0}:", dest);
            for (Flight f : results) {
                LOGGER.log(Level.INFO, " - {0} : {1} -> {2} (Seats: {3})",
                        new Object[]{f.getFlightNo(), f.getOrigin(), f.getDestination(), f.getSeats()});
            }
        }
    }

    // Helper Method 4: List Flights Logic
    private static void handleListFlights(Map<String, Flight> flights) {
        if (flights.isEmpty()) {
            LOGGER.info("No flights loaded.");
        } else {
            LOGGER.info("All flights:");
            for (Flight f : flights.values()) {
                LOGGER.log(Level.INFO, " - {0} : {1} -> {2} (Seats: {3})",
                        new Object[]{f.getFlightNo(), f.getOrigin(), f.getDestination(), f.getSeats()});
            }
        }
    }
}