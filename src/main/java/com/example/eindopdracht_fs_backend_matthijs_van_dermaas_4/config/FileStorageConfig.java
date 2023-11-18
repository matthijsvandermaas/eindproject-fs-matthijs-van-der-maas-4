package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.config;

import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.repository.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Configuration
public class FileStorageConfig {
private static ProductRepository  productRepository;
    private static String uploadDir;

    @Bean
    public String uploadDir() {
        return uploadDir;
    }



    public void loadFile() throws IOException {
        Resource resource = new ClassPathResource("data.sql");

        try (InputStream inputStream = resource.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processSqlLine(line);
            }
        }
    }

    private void processSqlLine(String sqlLine) {
        String[] parts = sqlLine.split("\\s+");
        String tableName = null;
        if (parts.length >= 3 && parts[0].equalsIgnoreCase("INSERT") && parts[1].equalsIgnoreCase("INTO")) {
            tableName = parts[2];
            if (tableName.equalsIgnoreCase("product")) {
                System.out.println("Product");
            } else if (tableName.equalsIgnoreCase("file_document")) {
                System.out.println("File_document");

            }
        }
    }

    public static String saveFile(MultipartFile file, FileStorageConfig fileStorageConfig) throws IOException {
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path uploadPath = Files.createDirectories(Paths.get(uploadDir));
        try (InputStream inputStream = file.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException e) {
            throw new IOException("Fout bij het opslaan van het bestand", e);
        }
    }
}
