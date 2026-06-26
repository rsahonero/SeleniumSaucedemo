package com.selenium.pom.utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.selenium.pom.model.LoginData;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    public static List<LoginData> readUsers(String filePath) {
        List<LoginData> users = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> rows = reader.readAll();

            // Saltar encabezado (índice 0)
            for (int i = 1; i < rows.size(); i++) {
                String[] data = rows.get(i);
                if (data.length >= 2) {
                    String username = data[0].trim();
                    String password = data[1].trim();
                    users.add(new LoginData(username, password));
                }
            }
        } catch (IOException | CsvException ex) {
            ex.printStackTrace();
        }

        return users;
    }
}
