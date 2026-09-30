package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CsvReader {

    private CsvReader() {
    }

    public static List<String[]> readCsv(String filePath) {
        return readCsv(filePath, true);
    }

    public static List<String[]> readCsv(String filePath, boolean skipHeader) {
        List<String[]> rows = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                if (firstLine && skipHeader) {
                    firstLine = false;
                    continue;
                }
                firstLine = false;
                rows.add(line.split(",", -1));
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read CSV file: " + filePath, e);
        }
        return rows;
    }


    public static Object[][] toDataProviderArray(String filePath) {
        List<String[]> rows = readCsv(filePath);
        Object[][] data = new Object[rows.size()][];
        for (int i = 0; i < rows.size(); i++) {
            data[i] = rows.get(i);
        }
        return data;
    }
}
