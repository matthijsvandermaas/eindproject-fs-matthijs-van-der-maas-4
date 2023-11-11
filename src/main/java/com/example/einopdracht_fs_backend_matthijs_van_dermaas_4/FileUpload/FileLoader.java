package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.FileUpload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class FileLoader {

    @Autowired
    private ResourceLoader resourceLoader;

    public void loadFile() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:src/main/resources/data.sql");
        try (InputStream inputStream = resource.getInputStream(); BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processSqlLine(line);
            }
        }
    }

    private void processSqlLine(String sqlLine) {
        String[] parts = sqlLine.split("\\s+");
        if (parts.length >= 3 && parts[0].equalsIgnoreCase("INSERT") && parts[1].equalsIgnoreCase("INTO")) {
            String tableName = parts[2];
            if (tableName.equalsIgnoreCase("product")) {
                // Voeg hier je logica toe om de SQL-regel die betrekking heeft op 'product' te verwerken
            }
        }
    }

    public void FileLoader() {
    }
}
