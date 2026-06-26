package com.selenium.pom.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.selenium.pom.model.LoginData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {

    // Obtiene los datos de un JSON
    public static List<LoginData> readUsersSafe(String filePath) {
        try {
            // Retorna los datos en un array Java
            return readUsers(filePath);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo JSON: " + filePath);
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static List<LoginData> readUsers(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(
                new File(filePath),
                new TypeReference<List<LoginData>>() {}
        );
    }
}