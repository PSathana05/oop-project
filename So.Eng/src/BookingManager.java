import java.util.*;

public class BookingManager {
    public boolean bookTicket(Map<String, Flight> flights, Passenger passenger, String flightNo, String bookingFile) {
        if (!flights.containsKey(flightNo)) {
            System.out.println("Invalid Flight Number!");
            return false;
        }
        Flight flight = flights.get(flightNo);
        if (flight.getSeats() <= 0) {
            System.out.println("No seats available!");
            return false;
        }
        flight.setSeats(flight.getSeats() - 1);

        Booking booking = new Booking(passenger, flight);
        FileUtils.writeFile(bookingFile, Arrays.asList(booking.toString()), true);
        System.out.println("Booking successful for " + passenger.getName() + " on flight " + flightNo);
        return true;
    }

    public boolean cancelTicket(Map<String, Flight> flights, Passenger passenger, String flightNo, String bookingFile, String cancelFile) {
        List<String> bookings = FileUtils.readFile(bookingFile);
        List<String> updated = new ArrayList<>();
        boolean found = false;

        for (String line : bookings) {
            String[] parts = line.split(",");
            if (parts.length == 2 && parts[0].trim().equals(passenger.getName()) && parts[1].trim().equals(flightNo) && !found) {
                found = true;
                continue; // skip (remove booking)
            }
            updated.add(line);
        }

        if (!found) {
            System.out.println("Booking not found!");
            return false;
        }

        FileUtils.writeFile(bookingFile, updated, false);
        FileUtils.writeFile(cancelFile, Arrays.asList(passenger.getName() + "," + flightNo), true);
        Flight f = flights.get(flightNo);
        if (f != null) f.setSeats(f.getSeats() + 1);
        System.out.println("Ticket for " + passenger.getName() + " on flight " + flightNo + " cancelled.");
        return true;
    }
}
