public class Booking {
    // change the fields into final
    private final Passenger passenger;
    private final Flight flight;

    public Booking(Passenger passenger, Flight flight) {
        // Null checks validation
        if (passenger == null) {
            throw new IllegalArgumentException("Passenger cannot be null");
        }
        if (flight == null) {
            throw new IllegalArgumentException("Flight cannot be null");
        }
        this.passenger = passenger;
        this.flight = flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }
    public Flight getFlight() {
        return flight;
    }

    @Override
    public String toString() {
        return passenger.getName() + "," + flight.getFlightNo();
    }
}
