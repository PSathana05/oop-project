import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUtils {

    private static final Logger LOGGER =
            Logger.getLogger(FileUtils.class.getName());

    private FileUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static List<String> readFile(String filename) {
        List<String> lines = new ArrayList<>();
        File file = new File(filename);

        if (!file.exists()) {
            return lines;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e,
                    () -> "Error reading file: " + filename);
        }

        return lines;
    }

    public static void writeFile(String filename, List<String> lines, boolean append) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, append))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e,
                    () -> "Error writing file: " + filename);
        }
    }

    public static boolean exists(String filename) {
        return new File(filename).exists();
    }
}