import java.io.*;
import java.util.*;

public class FileUtils {
    public static List<String> readFile(String filename) {
        List<String> lines = new ArrayList<>();
        File file = new File(filename);
        if (!file.exists()) return lines;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) lines.add(line);
        } catch (IOException e) {
            System.out.println("Error reading file: " + filename + " -> " + e.getMessage());
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
            System.out.println("Error writing file: " + filename + " -> " + e.getMessage());
        }
    }

    public static boolean exists(String filename) {
        return new File(filename).exists();
    }
}
