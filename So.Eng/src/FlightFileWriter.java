import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FlightFileWriter {

    private static final Logger LOGGER = Logger.getLogger(FlightFileWriter.class.getName());

    // Private constructor to prevent object creation
    private FlightFileWriter() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static boolean writeFile(String flightsFile) {
        try (FileWriter writer = new FileWriter(flightsFile)) {

            writer.write("UL101,Colombo,London,5\n");
            writer.write("EK225,Dubai,New York,10\n");
            writer.write("QR320,Doha,Paris,8\n");
            writer.write("SQ305,Singapore,Colombo,12\n");
            writer.write("BA142,London,Delhi,6\n");
            writer.write("AI274,Delhi,Colombo,7\n");
            writer.write("CX707,Hong Kong,Singapore,9\n");
            writer.write("QF402,Sydney,Melbourne,15\n");
            writer.write("MH116,Kuala Lumpur,Perth,10\n");
            writer.write("TK730,Istanbul,Colombo,11\n");

            return true;

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error writing to file: {0}", e.getMessage());
            return false;
        }
    }
}