import java.io.FileWriter;
import java.io.IOException;

public class FlightFileWriter{
    private FlightFileWriter(){
        throw new IllegalStateException("Utility class");
    }
    public static boolean writeFile(String flightsFile) {
        try (
            FileWriter writer = new FileWriter(flightsFile)){ // creates or overwrites file
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
            //remove System.out.println
            return false;
        }
    }

}
