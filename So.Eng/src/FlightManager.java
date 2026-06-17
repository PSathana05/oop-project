import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FlightManager {
    private Map<String, Flight> flights = new LinkedHashMap<>();

    public void loadFlights(String filename) {
        List<String> lines = FileUtils.readFile(filename);
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 4) {
                try {
                    String fn = parts[0].trim();
                    String origin = parts[1].trim();
                    String dest = parts[2].trim();
                    int seats = Integer.parseInt(parts[3].trim());
                    flights.put(fn, new Flight(fn, origin, dest, seats));
                } catch (NumberFormatException ignored) { }
            }
        }
    }

    public void saveFlights(String filename) {
        List<String> lines = new ArrayList<>();
        for (Flight f : flights.values()) lines.add(f.toString());
        FileUtils.writeFile(filename, lines, false);
    }

    public Map<String, Flight> getFlights() { return flights; }

    private void recursiveSearchHelper(List<String> keys, String destination, int idx, List<Flight> results) {
        if (idx >= keys.size()) return;
        String key = keys.get(idx);
        Flight f = flights.get(key);
        if (f != null && f.getDestination().equalsIgnoreCase(destination)) results.add(f);
        recursiveSearchHelper(keys, destination, idx + 1, results);
    }

    public List<Flight> searchFlightsByDestination(String destination) {
        List<String> keys = new ArrayList<>(flights.keySet());
        List<Flight> results = new ArrayList<>();
        recursiveSearchHelper(keys, destination, 0, results);
        return results;
    }

    public void addFlight(Flight f) {
        flights.put(f.getFlightNo(), f);
    }
}
