public class Flight {
    private String flightNo;
    private String origin;
    private String destination;
    private int seats;

    public Flight(String flightNo, String origin, String destination, int seats) {
        this.flightNo = flightNo;
        this.origin = origin;
        this.destination = destination;
        this.seats = seats;
    }

    public String getFlightNo() { return flightNo; }
    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public int getSeats() { return seats; }

    public void setSeats(int seats) { this.seats = seats; }

    @Override
    public String toString() {
        return flightNo + "," + origin + "," + destination + "," + seats;
    }
}
