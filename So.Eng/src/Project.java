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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FlightManager flightManager = new FlightManager();
        BookingManager bookingManager = new BookingManager();
        ReportManager reportManager = new ReportManager();

        ensureSampleFlightsExist();

        flightManager.loadFlights(FLIGHTS_FILE);
        Map<String, Flight> flights = flightManager.getFlights();

        System.out.println("Loaded " + flights.size() + " flights.");

        while (true) {
            showMenu();
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Enter Flight No: ");
                    String fno = sc.nextLine().trim();
                    System.out.print("Enter Passenger Name: ");
                    Passenger p1 = new Passenger(sc.nextLine().trim());
                    if (bookingManager.bookTicket(flights, p1, fno, BOOKINGS_FILE))
                        flightManager.saveFlights(FLIGHTS_FILE);
                    break;

                case "2":
                    System.out.print("Enter Flight No: ");
                    String cno = sc.nextLine().trim();
                    System.out.print("Enter Passenger Name: ");
                    Passenger p2 = new Passenger(sc.nextLine().trim());
                    if (bookingManager.cancelTicket(flights, p2, cno, BOOKINGS_FILE, CANCELLATIONS_FILE))
                        flightManager.saveFlights(FLIGHTS_FILE);
                    break;

                case "3":
                    System.out.print("Enter Destination: ");
                    String dest = sc.nextLine().trim();
                    List<Flight> results = flightManager.searchFlightsByDestination(dest);
                    if (results.isEmpty()) {
                        System.out.println("No flights found to " + dest + ".");
                    } else {
                        System.out.println("Available flights to " + dest + ":");
                        for (Flight f : results) {
                            System.out.println(" - " + f.getFlightNo() + " : " + f.getOrigin() + " -> " + f.getDestination() + " (Seats: " + f.getSeats() + ")");
                        }
                    }
                    break;

                case "4":
                    reportManager.generateReport(BOOKINGS_FILE, CANCELLATIONS_FILE, REPORT_FILE);
                    break;

                case "5":
                    if (flights.isEmpty()) System.out.println("No flights loaded.");
                    else {
                        System.out.println("All flights:");
                        for (Flight f : flights.values()) {
                            System.out.println(" - " + f.getFlightNo() + " : " + f.getOrigin() + " -> " + f.getDestination() + " (Seats: " + f.getSeats() + ")");
                        }
                    }
                    break;

                case "6":
                    System.out.println("Exiting system... Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Enter 1–6.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n--- Airline Reservation System ---");
        System.out.println("1. Book Ticket");
        System.out.println("2. Cancel Ticket");
        System.out.println("3. Search Flights (by Destination)");
        System.out.println("4. Generate Report");
        System.out.println("5. List All Flights");
        System.out.println("6. Exit");
        System.out.print("Enter choice: ");
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
