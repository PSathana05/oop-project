import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class BookingManager {

    private static final Logger LOGGER =
            Logger.getLogger(BookingManager.class.getName());

    private static final String BOOKING_NOT_FOUND = "Booking not found!";
    private static final String INVALID_FLIGHT = "Invalid Flight Number!";
    private static final String NO_SEATS = "No seats available!";

    public boolean bookTicket(Map<String, Flight> flights,
                              Passenger passenger,
                              String flightNo,
                              String bookingFile) {

        if (flights == null || passenger == null || flightNo == null) {
            return false;
        }

        Flight flight = flights.get(flightNo);

        if (flight == null) {
            LOGGER.warning(INVALID_FLIGHT);
            return false;
        }

        if (flight.getSeats() <= 0) {
            LOGGER.warning(NO_SEATS);
            return false;
        }

        flight.setSeats(flight.getSeats() - 1);

        Booking booking = new Booking(passenger, flight);

        List<String> record = new ArrayList<>();
        record.add(booking.toString());

        FileUtils.writeFile(bookingFile, record, true);

        LOGGER.info("Booking successful for " + passenger.getName());

        return true;
    }

    private boolean isSameBooking(String line, String name, String flightNo) {
        String[] parts = line.split(",");
        return parts.length == 2
                && parts[0].trim().equals(name)
                && parts[1].trim().equals(flightNo);
    }

    public boolean cancelTicket(Map<String, Flight> flights,
                                Passenger passenger,
                                String flightNo,
                                String bookingFile,
                                String cancelFile) {

        if (flights == null || passenger == null || flightNo == null) {
            return false;
        }

        List<String> bookings = FileUtils.readFile(bookingFile);
        //fix: resolve null checks and improve booking validation
        if (bookings == null || bookings.isEmpty()) {

            LOGGER.warning(BOOKING_NOT_FOUND);
            return false;
        }

        String name = passenger.getName();
        List<String> updated = new ArrayList<>();
        boolean found = false;

        for (String line : bookings) {

            if (line == null || line.isBlank()) {
                continue;
            }

            if (!found && isSameBooking(line, name, flightNo)) {
                found = true;
                continue;
            }

            updated.add(line);
        }

        if (!found) {
            LOGGER.warning(BOOKING_NOT_FOUND);
            return false;
        }

        FileUtils.writeFile(bookingFile, updated, false);
//refactor: reduce cognitive complexity in cancelTicket method
        List<String> cancelRecord = new ArrayList<>();
        cancelRecord.add(name + "," + flightNo);

        FileUtils.writeFile(cancelFile, cancelRecord, true);

        Flight flight = flights.get(flightNo);
        if (flight != null) {
            flight.setSeats(flight.getSeats() + 1);
        }
//refactor: replace System.out with logger for SonarQube compliance
        LOGGER.info("Ticket cancelled for " + name);

        return true;
    }
}